package pl.edu.pw.mmajews2.photophoto.taking.tasks;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Maciej Majewski on 2016-05-02.
 */
public class SaveMainBitmapTask extends CameraTask {
    private String filename;

    public SaveMainBitmapTask(CameraTaskContext context, String filename) {
        super(context);
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), filename));

            context.getMainPictureBitmap().compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            context.setMainPictureBitmap(null);

            postNextTask();
        } catch (IOException e) {
            Log.e(TAG, "Bitmap save failure", e);
        }
    }
}
