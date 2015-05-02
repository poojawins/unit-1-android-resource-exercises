package nyc.c4q.unit_1_android_resource_exercises;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.util.Log;

import junit.framework.Assert;

import java.io.File;

import dalvik.annotation.TestTarget;

public class ActivityTest extends ActivityInstrumentationTestCase2<ResourceExercisesActivity> {

    public static final String TAG = "ActivityTest";

    ResourceExercisesActivity mActivity;

    public ActivityTest() {
        super(ResourceExercisesActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity(); // This will actually start the activity
        getInstrumentation().waitForIdleSync(); // Wait for animations to complete and acitvity enter idle state
        // Setup logic goes here
    }

    public void testStringsXML() {
        Resources resources = mActivity.getApplicationContext().getResources();
        int helloWorldIdentifier = resources.getIdentifier("hello_world", "string", mActivity.getPackageName());
        int imSorryDaveIdentifier = resources.getIdentifier("im_sorry_dave", "string", mActivity.getPackageName());

        Assert.assertEquals("Hello world!", resources.getString(helloWorldIdentifier));
        Assert.assertEquals("I'm sorry Dave, I'm afraid I can't let you do that.",
                resources.getString(imSorryDaveIdentifier));
    }

    // TODO internationalization

    public void testDrawableRed() {
        // make the "res/drawable" folder, then
        // put "hello_red.png" in it

        Resources resources = mActivity.getApplicationContext().getResources();
        int drawableRedIdentifier = resources.getIdentifier("hello_red", "drawable", mActivity.getPackageName());
        Bitmap bm = ((BitmapDrawable) resources.getDrawable(drawableRedIdentifier)).getBitmap();

        Assert.assertEquals("actual height value: " + bm.getHeight(), 40, bm.getHeight());
        Assert.assertEquals("actual width value: " + bm.getWidth(), 40, bm.getWidth());
        Assert.assertEquals(0xFFFF0000, bm.getPixel(0, 0));
    }
    public void testDrawableBlue() {
        // make the "res/drawable" folder, then
        // put "hello_blue.png" in it

        Resources resources = mActivity.getApplicationContext().getResources();
        int drawableBlueIdentifier = resources.getIdentifier("hello_blue", "drawable", mActivity.getPackageName());
        Bitmap bm = ((BitmapDrawable) resources.getDrawable(drawableBlueIdentifier)).getBitmap();

        Assert.assertEquals(40, bm.getHeight());
        Assert.assertEquals(40, bm.getWidth());
        Assert.assertEquals(0xFF0000FF, bm.getPixel(0, 0));
    }

    public void testLayout() {

    }

    public void testLayoutDensity() {
        // hdpi
        // mdpi
        // ldpi (low density?)
    }
}