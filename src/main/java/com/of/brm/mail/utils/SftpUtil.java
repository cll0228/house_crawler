package com.of.brm.mail.utils;

import com.jcraft.jsch.*;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class SftpUtil {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SftpUtil.class);

    public static void main(String[] args) throws Exception {
        SftpUtil util = new SftpUtil();
        ChannelSftp appuser = util.connect("192.168.200.34", 22, "appuser", "JOf3eJ)Fj");
        util.download("/tmp", "bill.txt", "d:/bill.txt", appuser);
        disconnects(appuser);
        System.out.println("00000");
    }

    /**
     * Disconnect with server
     *
     * @throws Exception
     */
    public static void disconnects(ChannelSftp sftp) throws Exception {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.getSession().disconnect();
            } else if (sftp.isClosed()) {
                LOGGER.debug(" sftp is closed already");
            }
        }
    }

    /**
     * 连接sftp服务器
     *
     * @throws Exception
     */
    public ChannelSftp connect(String host, int port, String username, String password) throws Exception {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(username, host, port);

            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect(20000);

            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            LOGGER.error("Connect error", e);
        }
        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     */
    public void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            LOGGER.error("Upload error", e);
        }
    }

    /**
     * 下载文件
     *
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            LOGGER.error("Download " + downloadFile + "/" + downloadFile + " error", e);
        }
    }

    public void uploadByDirectory(String directory, ChannelSftp sftp) throws Exception {

        String uploadFile = "";
        List<String> uploadFileList = this.listFiles(directory, sftp);
        Iterator<String> it = uploadFileList.iterator();

        while (it.hasNext()) {
            uploadFile = it.next().toString();
            this.upload(directory, uploadFile, sftp);
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @return list 文件名列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<String> listFiles(String directory, ChannelSftp sftp) throws Exception {
        Vector fileList;
        List<String> fileNameList = new ArrayList<>();

        fileList = sftp.ls(directory);
        Iterator it = fileList.iterator();

        while (it.hasNext()) {
            String fileName = ((ChannelSftp.LsEntry) it.next()).getFilename();
            if (".".equals(fileName) || "..".equals(fileName)) {
                continue;
            }
            if (fileName.endsWith("_.xlsx")) {
                fileNameList.add(fileName);
            }
        }
        return fileNameList;
    }

    /**
     * 创建文件夹
     *
     * @param directory 要创建的文件夹所在目录
     * @param fileName 要创建的文件名字
     * @param sftp
     */
    // public void mvPath(String oldPath, String newPath, ChannelSftp sftp) {
    // try {
    // sftp.cd(directory);
    // sftp.mv(oldPath, newPath);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    /**
     * 删除文件
     *
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            LOGGER.error("Delete " + directory + "/" + deleteFile + " error", e);
        }
    }

    /**
     * 创建文件夹
     *
     * @param directory 要创建的文件夹所在目录
     * @param fileName 要创建的文件名字
     * @param sftp
     */
    public void createDir(String directory, String fileName, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.mkdir(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更改文件名称
     *
     * @param directory
     * @param sftp
     * @throws SftpException
     */
    public void rename(String directory, ChannelSftp sftp) throws SftpException {
        Vector fileList = sftp.ls(directory);
        Iterator it = fileList.iterator();
        sftp.cd(directory);
        while (it.hasNext()) {
            String fileName = ((ChannelSftp.LsEntry) it.next()).getFilename();
            if (".".equals(fileName) || "..".equals(fileName)) {
                continue;
            }
            try {
                sftp.rename(fileName, fileName + "_.xlsx");
            } catch (SftpException e) {
                LOGGER.error("重命名失败！" + fileName);
            }
        }

    }
}
