package com.twoculture.twoculture.tools;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;


public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public static void saveFileToExtensionStorage(File sourceFile) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/healthcheck");
        myDir.mkdirs();

        File file = new File(myDir, sourceFile.getName());
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            FileInputStream in = new FileInputStream(sourceFile);
            int length = (int) sourceFile.length();
            byte[] bytes = new byte[length];
            in.read(bytes);
            out.write(bytes);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap ResizeBitmap(Bitmap bitmap, int scale) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(1 / scale, 1 / scale);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        bitmap.recycle();
        return resizedBitmap;
    }


    public static byte[] toByteArray(File file, int len) {
        FileInputStream fileInputStream = null;
        byte[] bFiles = new byte[len];
        if (!file.exists()) return bFiles;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFiles, 0, len);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bFiles;
    }

    public static File getFilesDir(Context context) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        try {
            final File cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ?
                    getExternalFilesDir(context) : context.getFilesDir();
            return cachePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getExternalFilesDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            File f = context.getExternalFilesDir(null);
            if (f != null)
                return f;
        }

        final String dir = "/Android/data/" + context.getPackageName() + "/files/";
        File extFile = Environment.getExternalStorageDirectory();
        return new File(extFile.getPath() + dir);
    }


    public static String getPath(Uri uri, Context cxt) {
        if (uri != null) {
            Cursor cursor = cxt.getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = cxt.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return path;
        }
        return null;
    }

    public static String getRealPath(Uri fileUrl) {
        File file = new File(fileUrl.getPath());
        return file.getPath();
    }

    public static void removeDir(File dir) {
        if (dir.isFile()) {
            dir.delete();
            return;
        }
        File[] childFiles = dir.listFiles();
        if (childFiles != null && childFiles.length > 0) {
            for (File file : childFiles) {
                if (file.isFile()) {
                    file.delete();
                } else {
                    removeDir(file);
                }
            }
        }
        dir.delete();
    }

    public static String getFormatFileSize(String file) {
        File f = new File(file);
        String fileSizeString = "0MB";
        if (f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                long fileS = fis.available();
                fileSizeString = getFormatSizeStr(fileS);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileSizeString;
    }

    public static String getFormatSizeStr(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static int getFileSize(String file, FileSizeUnit unit) {
        File f = new File(file);
        int result = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                long fileS = fis.available();

                if (unit == FileSizeUnit.B) {
                    result = (int) Math.ceil(fileS);
                } else if (unit == FileSizeUnit.K) {
                    result = (int) Math.ceil((double) fileS / 1024);
                } else if (unit == FileSizeUnit.G) {
                    result = (int) Math.ceil((double) fileS / 1073741824);
                } else {
                    result = (int) Math.ceil((double) fileS / 1048576);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (result == 0) result = 1;
        return result;
    }

    public static void compressFile(File src, long maxSize, int maxW, int maxH, File target) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();

        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(src.getAbsolutePath(), newOpts);
        byte[] bytes = FileUtils.toByteArray(src, 4);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        int be = 1;
        if (w > h && w > maxW) {
            be = (int) (1.0f * newOpts.outWidth / maxW);
        } else if (w < h && h > maxH) {
            be = (int) (1.0f * newOpts.outHeight / maxH);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;

        Bitmap newBitmap = BitmapFactory.decodeFile(src.getAbsolutePath(), newOpts);
        compressImage(newBitmap, maxSize, target);
        if (bitmap != null) {
            bitmap.recycle();
        }
        if (newBitmap != null)
            newBitmap.recycle();
    }

    private static void compressImage(Bitmap image, long maxSize, File target) {
        FileOutputStream fos = null;
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream isBm = null;
        try {
            baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            int options = 100;
            while (baos.toByteArray().length > maxSize) {
                baos.reset();
                options -= 10;
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            }
            isBm = new ByteArrayInputStream(baos.toByteArray());
            fos = new FileOutputStream(target);
            baos.writeTo(fos);
        } catch (IOException e) {
            Log.e(TAG, "IO Exception", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception", e);
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception", e);
                }
            }
            if (isBm != null) {
                try {
                    isBm.close();
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception", e);
                }
            }
        }
    }

    public enum FileSizeUnit {
        B, K, M, G
    }

}
