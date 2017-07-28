package kh.com.truemoney.sdk.barcode;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;



/**
 * Created by LIM RAVY on 25-07-2017.
 */

public final class BarcodeUtils {

    static String TAG ="truebarcodelib  ==>";

    // Method scan barcode and QR code
    public static void scanBarcodeAndQRcode(int REQUEST_CODE, Context context, String Prompt) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE);
        } else {
            IntentIntegrator integrator = new IntentIntegrator((Activity) context);
            integrator.setPrompt(Prompt);
            integrator.setCameraId(0);
            integrator.setResultDisplayDuration(0);
            if (integrator == null) {
                integrator = new IntentIntegrator((Activity) context);
                integrator.setPrompt("");
                integrator.setCameraId(0);
                integrator.setResultDisplayDuration(0);
                integrator.initiateScan();
            } else {
                integrator.initiateScan();
            }
        }
    }


    /** ==> Method generate QRcode
     * Up to 1K UTF-8 characters ( Example : Abc 123 )
     */
    public static void generateQRcode(String data, ImageView displayView, int width) {
        //Encode with a QR Code image
        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(data,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.QR_CODE.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateQRcode recognized only :  Up to 1K UTF-8 characters ( Example : Abc 123 )");
        }
    }


    /** ==> Method generate barcode 128
     * Up to 80 ASCII characters from 0x20 to 0x7E (Example: Abc 123)
     * TODO recomment to use
     */
    public static void generateBarcode128(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.CODE_128.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcode128 recognized only :  Up to 80 ASCII characters from 0x20 to 0x7E (Example: Abc 123)");
        }
    }

    /**
     * ==> Method generate barcode 128
     * Up to 80 ASCII characters from 0x20 to 0x7E (Example: Abc 123)
     * TODO recomment to use
     */
    public static void generateBarcode128(String data, ImageView displayView, int width, int height) {
        String finaldata = Uri.encode(data, "utf-8");
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(finaldata, BarcodeFormat.CODE_128, width, height);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            displayView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "generateBarcode128 recognized only :  Up to 80 ASCII characters from 0x20 to 0x7E (Example: Abc 123)");
        }
    }

    /** ==> Method generate barcode 39
     *    Up to 43 characters containning only A-Z,1-9,-,.,space,$,/,+,%,*
     *    (example :ABC 1)
     */
    public static void generateBarcode39(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.CODE_39.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcode39 recognized only :  Up to 43 characters containning only A-Z,1-9,-,.,space,$,/,+,%,*  (example :ABC 1)");
        }
    }


    /** ==>  Method generate barcode codebar
     * Up to 1K charactors containing only 1234567890-$:/.+.
     */
    public static void generateBarcodebar(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.CODABAR.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodebar recognized only :  Up to 1K charactors containing only 1234567890-$:/.+.");
        }
    }


    // Method generate barcode 93
    public static void generateBarcode93(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.CODE_93.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcode93 recognized only : Data not recognized by this method");
        }
    }

    /** ==>  Method generate barcode MAXICODE
     *  Up to 1K ASCII characters (Example: Abc 123)
     */
    public static void generateBarcodeMAXICODE(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.MAXICODE.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodeMAXICODE recognized only : Up to 1K ASCII characters (Example: Abc 123)");
        }
    }


    /** ==>  Method generate barcode AZTEC
     *  Up to 1K UTF-8 or ISO 8859-1 characters (example : Abc 123)
     */
    public static void generateBarcodeAZTEC(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.AZTEC.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodeAZTEC recognized only : Up to 1K UTF-8 or ISO 8859-1 characters (example : Abc 123)");
        }
    }


    /** ==>  Method generate barcode EAN_8
     *  8 digits (example: 12345678)
     */
    public static void generateBarcodeEAN_8(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.EAN_8.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodeEAN_8 recognized only :  8 digits (example: 12345678)");
        }
    }


    /** ==>  Method generate barcode EAN_13
     *  13 digits (example: 1234567890123)
     */
    public static void generateBarcodeEAN_13(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.EAN_13.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodeEAN_13 recognized only :  13 digits (example: 1234567890123)");
        }
    }


    /** ==>  Method generate barcode UPC_A
     *  11 to 12 digits (example: 123456789012)
     */
    public static void generateBarcodeUPC_A(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.UPC_A.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodeUPC_A recognized only :  11 to 12 digits (example: 123456789012)");
        }
    }



    /** ==>  Method generate barcode ITF
     *  Up to 40 digits pairs (example: 1234)
     */
    public static void generateBarcodeITF(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.ITF.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodeITF recognized only :  Up to 40 digits pairs (example: 1234)");
        }
    }



    /** ==>  Method generate barcode PDF_417
     *  Up to 1K ASCII characters (example : Abc 123)
     */
    public static void generateBarcodePDF_417(String data, ImageView displayView, int width) {
        String finaldata = Uri.encode(data, "utf-8");
        QRCodeEncoder qrCodeEncoder1 = new QRCodeEncoder(finaldata,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.PDF_417.toString(),
                width);
        try {
            Bitmap bitmap = qrCodeEncoder1.encodeAsBitmap();
            displayView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            Log.e(TAG ,"generateBarcodePDF_417 recognized only :  Up to 1K ASCII characters (example : Abc 123)");
        }
    }


}
