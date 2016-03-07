package com.xa3ti.base.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QRCoderUtil {
    private static final int WIDTH = 400;

    private static final int HEIGHT = 400;

    public static boolean encQRCode(String qrContent, String storePath) {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//       ErrorCorrectionLevel.L           修正错误等级 L最小 7%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(qrContent, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            File qrfile = new File(storePath);
            if (qrfile.exists())
            {
                qrfile.delete();
            }
            qrfile.getParentFile().mkdirs();
            qrfile.createNewFile();
            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(storePath));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Object decQRCode(String storePath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(storePath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) throws IOException {
//        String imgPath = "d:/image3/";
//        for (int i = 0;i<3;i++)
//        {
//            String temp =  "http://m.sigma-photo.com.cn";
//            UUID uuid = UUID.randomUUID();
//            String content= temp+"/credits/qrCoder?"+"cdk="+ uuid;
//            String fileName = i+".png";
//            QRCoderUtil.encQRCode(content, imgPath+fileName);
//        }

        write();
//        System.out.println("You have finished zxing encode.");

//        imgPath = "d:/qrcode.png";
//        String decodeContent = (String) QRCoderUtil.decQRCode("", imgPath);
//        System.out.println(decodeContent);
//        System.out.println("You have finished zxing decode.");
    }

    private static void write() throws IOException {
        String path = "d:/image3/";

        File file = new File(path+"cdk.text");// 指定要写入的文件
        if (!file.exists()) {// 如果文件不存在则创建
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (int i=0;i<3;i++)
        {
            String str = (String) QRCoderUtil.decQRCode(path + i + ".png");
            str += ",image="+i;
            System.out.println("str = " + str);
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流
    }
}
