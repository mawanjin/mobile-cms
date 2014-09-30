package com.joinsoft.mobile.cms.component;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: xingsen
 * Date: 14-2-26
 * Time: 下午1:07
 */
public class VirtualFile {
    protected static final char EXTENSION_SEPARATOR = '.';
    /**
     * The Unix separator character.
     */
    protected static final char UNIX_SEPARATOR = '/';
    /**
     * The Windows separator character.
     */
    protected static final char WINDOWS_SEPARATOR = '\\';

    protected File realFile;
    private String root = "";//empty string

    public VirtualFile(File file) {
        this.realFile = file;
    }

    public VirtualFile(File file, String root) {
        this.realFile = file;
        this.root = root;
    }

    public String getName() {
        return realFile.getName();
    }

    public boolean isDirectory() {
        return realFile.isDirectory();
    }

    public List<VirtualFile> list() {
        return list(null);
    }

    public List<VirtualFile> list(FileFilter fileFilter) {
        List<VirtualFile> res = new ArrayList<VirtualFile>();
        if (exists()) {
            File[] children = realFile.listFiles();
            for (int i = 0; i < children.length; i++) {
                if (fileFilter == null) {
                    res.add(new VirtualFile(children[i], root));
                    continue;
                }
                if (fileFilter.accept(children[i])) {
                    res.add(new VirtualFile(children[i], root));
                }
            }
        }
        return res;
    }

    public boolean exists() {
        try {
            if (realFile != null) {
                return realFile.exists();
            }
            return false;
        } catch (AccessControlException e) {
            return false;
        }
    }

    public Long lastModified() {
        if (realFile != null) {
            return realFile.lastModified();
        }
        return 0L;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof VirtualFile) {
            VirtualFile vf = (VirtualFile) other;
            if (realFile != null && vf.realFile != null) {
                return realFile.equals(vf.realFile);
            }
        }
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        if (realFile != null) {
            return realFile.hashCode();
        }
        return super.hashCode();
    }

    public long length() {
        return realFile.length();
    }

    public VirtualFile child(String name) {
        return new VirtualFile(new File(realFile, name), root);
    }

    public void setExecutable(boolean executable, boolean ownOnly) throws IOException {
        if (!realFile.exists()) {
            realFile.createNewFile();
        }
        realFile.setExecutable(executable, ownOnly);
    }

    public Channel channel() {
        try {
            FileInputStream fis = new FileInputStream(realFile);
            FileChannel ch = fis.getChannel();
            return ch;
        } catch (FileNotFoundException e) {
            return null;
        }

    }

    public static VirtualFile open(String file) {
        return open(new File(file));
    }

    public static VirtualFile open(File file) {
        return new VirtualFile(file);
    }

    public File getRealFile() {
        return realFile;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static VirtualFile search(Collection<VirtualFile> roots, String path) {
        for (VirtualFile file : roots) {
            if (file.child(path).exists()) {
                return file.child(path);
            }
        }
        return null;
    }

    public void mkdirs() {
        this.realFile.mkdirs();
    }

    public String getAbsolutePath() {
        return realFile.getAbsolutePath();
    }

    public OutputStream getOutputStream(boolean append) throws IOException {
        if (!realFile.exists()) {
            realFile.getParentFile().mkdirs();
            realFile.createNewFile();
        }
        return new FileOutputStream(realFile, append);
    }


    public boolean delete() {
        if (!realFile.exists()) {
            return false;
        }
        if (realFile.isFile()) {
            return realFile.delete();
        }
        //递归删除
        List<VirtualFile> files = list();
        for (VirtualFile file : files) {
            file.delete();
        }
        return realFile.delete();
    }


    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(realFile);
    }

    public VirtualFile getParent() {
        return new VirtualFile(realFile.getParentFile(), root);
    }

    public String getRelativePath() {
        String relativePath = getAbsolutePath().substring(root.length());
        return relativePath.equals("") ? "/" : relativePath;
    }

    public String getExt() {
        return FilenameUtils.getExtension(realFile.getName());
    }

    public void writeString(String content) throws IOException {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(getOutputStream(false), "UTF-8");
            IOUtils.write(content, writer);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public String readContentAsString() throws IOException {
        InputStream in = null;
        try {
            in = getInputStream();
            return IOUtils.toString(in, "UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static class DirectoryFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            return false;
        }
    }

    public static class FileNameFilter implements FileFilter {
        private final Pattern pattern;

        public FileNameFilter(String regex) {
            pattern = Pattern.compile(regex);
        }

        @Override
        public boolean accept(File file) {
            if (pattern.matcher(file.getName()).matches()) {
                return true;
            }
            return false;
        }
    }
}
