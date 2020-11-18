package k12.core.opencv;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Vector;

import static org.opencv.core.CvType.CV_8U;
import static org.opencv.imgproc.Imgproc.MORPH_RECT;

/**
 * @Description TODO
 * @Author turnglight
 * @Date 2020/11/17 12:08
 * @Version 1.0.0
 */
public class Demo {


    public static String srcImage = "D://A4.jpg";
    public static String destImage = "D://A4-1.jpg";
    public static String dest1Image = "D://A4-2.jpg";
    public static String dest2Image = "D://A4-3.jpg";
    public static String dest3Image = "D://A4-4.jpg";

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        graying();
    }

    public static void graying(){
        Mat srcImgMat = Imgcodecs.imread(srcImage);
        Mat destImgMat = new Mat();
        Imgproc.cvtColor(srcImgMat, destImgMat, Imgproc.COLOR_RGB2GRAY);
        Imgcodecs.imwrite(destImage, destImgMat);
        //图片二值化
        Mat dest1ImgMat = new Mat();
        Imgproc.adaptiveThreshold(destImgMat, dest1ImgMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 255, 1);
        Imgcodecs.imwrite(dest1Image, dest1ImgMat);

        Mat dest2ImgMat = new Mat();
        //确定腐蚀和膨胀核的大小
        Mat element = Imgproc.getStructuringElement(MORPH_RECT, new Size(1, 6));
        //腐蚀操作
        Imgproc.erode(dest1ImgMat, dest2ImgMat, element);
        Imgcodecs.imwrite(dest2Image, dest2ImgMat);

        Mat dest3ImgMat = new Mat();
        //膨胀操作
        Imgproc.dilate(dest2ImgMat, dest3ImgMat, element);
        Imgcodecs.imwrite(dest3Image, dest3ImgMat);


        //确定每张答题卡的ROI区域
        Mat imag_ch1 = dest3ImgMat.submat(new Rect(200, 1065, 1930, 2210));


        //识别所有轮廓
        Vector<MatOfPoint> chapter1 = new Vector<>();
        Imgproc.findContours(imag_ch1, chapter1, new Mat(), 2, 3);
        Imgcodecs.imwrite("D://result1.jpg", imag_ch1);
        Mat result = new Mat(imag_ch1.size(), CV_8U, new Scalar(255));
        Imgcodecs.imwrite("D://result2.jpg", result);
        Imgproc.drawContours(result, chapter1, -1, new Scalar(0), 2);

        Imgcodecs.imwrite("D://result.jpg", result);

    }

}
