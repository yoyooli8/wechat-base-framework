package com.xa3ti.base.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
	/**
	 * @author tzhang
	 *
	 */
	@SuppressWarnings("restriction")
	public class ImageCut {
	    /**
	     * jpg图片格式
	     */
	    private static final String IMAGE_FORM_OF_JPG = "jpg";
	    /**
	     * png图片格式
	     */
	    private static final String IMAGE_FORM_OF_PNG = "png";
	    /**
	     * 剪切点x坐标
	     */
	    private int x;
	    /**
	     * 剪切点y坐标
	     */
	    private int y;
	    /**
	     * 剪切点宽度
	     */
	    private int width;

	    /**
	     * 剪切点高度
	     */
	    private int height;
	    public ImageCut() {}
	    public ImageCut(int x, int y, int width, int height) {
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	    }
	    /**
	     * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
	     * 参数：formatName - 包含非正式格式名称 .（例如 "jpeg" 或 "tiff"）等 。
	     * 
	     * @param postFix
	     *            文件的后缀名
	     * @return
	     */
	    public Iterator<ImageReader> getImageReadersByFormatName(String postFix) {
	        if(postFix.equals(IMAGE_FORM_OF_JPG)){
	        	return ImageIO.getImageReadersByFormatName(IMAGE_FORM_OF_JPG);
	        }else if(postFix.equals(IMAGE_FORM_OF_PNG)){
	        	return ImageIO.getImageReadersByFormatName(IMAGE_FORM_OF_PNG);
	        }else{
	        	return ImageIO.getImageReadersByFormatName(IMAGE_FORM_OF_JPG);
	        }
	    }
	    /**
	     * 对图片裁剪，并把裁剪完新图片保存 。
	     * @param srcpath 源图片路径
	     * @param subpath 剪切图片存放路径
	     * @throws IOException
	     */
		public String cutTemp(String filePath,String subpath) throws FileNotFoundException, IOException{
	    		 // 获取文件的后缀名
		            String postFix = getPostfix(filePath);
	    		 // 保存新图片
		            String imageName = subpath  + filePath.substring(filePath.lastIndexOf("/"),filePath.lastIndexOf(".")) + "." + postFix;
	    		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    	        image.getGraphics().drawImage(ImageIO.read(new File(filePath)), 0, 0, width, height, null);
	    	        FileOutputStream fos = new FileOutputStream(new File(imageName));
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
	    	        encoder.encode(image);
		            image.flush();
		            fos.flush();
		            fos.close();
		            return imageName;
	    }
	    public String cut(String fileName , InputStream input , String subpath) throws IOException {
	    		ImageInputStream iis = null;
	    	 try {
		            // 获取文件的后缀名
		            String postFix = getPostfix(fileName);
		           // System.out.println("图片格式为：" + postFix);
		            /*
		             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
		             * 参数：formatName - 包含非正式格式名称 .（例如 "jpeg" 或 "tiff"）等 。
		             */
		            Iterator<ImageReader> it = getImageReadersByFormatName(postFix);
		            ImageReader reader = it.next();
		            // 获取图片流
		            iis = ImageIO.createImageInputStream(input);
		            /*
		             * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
		             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
		             */
		            reader.setInput(iis, true);
		            /*
		             * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
		             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
		             * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
		             */
		            ImageReadParam param = reader.getDefaultReadParam();
		            /*
		             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
		             * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
		             */
		           // Rectangle rect = new Rectangle(x, y, width, height);
		            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
		           // param.setSourceRegion(rect);
		            /*
		             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
		             * BufferedImage 返回。
		             */
		            BufferedImage bi = reader.read(0, param);
		            // 保存新图片
		            //System.out.println(subpath);
		            UUID id = UUID.randomUUID();
		           // System.out.println(id);
		            String imageName = subpath  + id + "." + postFix;
		            System.out.println(subpath);
		            ImageIO.write(bi, postFix, new File(imageName));
		            return imageName;
		        } finally {
		            if (iis != null)
		                iis.close();
		        }
	    }
	    /**
	     * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"<br>
	     * 
	     * @param inputFilePath
	     * @return
	     */
	    public String getPostfix(String inputFilePath) {
	        return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
	    }
}
