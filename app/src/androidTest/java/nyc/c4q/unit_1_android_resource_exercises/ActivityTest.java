package nyc.c4q.unit_1_android_resource_exercises;

import android.app.Application;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.util.Log;

import junit.framework.Assert;

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

    // Make sure the test methods start with name "test"
    public void testHello() {
        Assert.assertNotNull("lol");
    }

    public void testStringsXML() {
        Resources resources = mActivity.getApplicationContext().getResources();
        int helloWorldIdentifier = resources.getIdentifier("hello_world", "string", mActivity.getPackageName());
        int imSorryDaveIdentifier = resources.getIdentifier("im_sorry_dave", "string", mActivity.getPackageName());

        Assert.assertEquals("Hello world!", resources.getString(helloWorldIdentifier));
        Assert.assertEquals("I'm sorry Dave, I'm afraid I can't let you do that.",
                resources.getString(imSorryDaveIdentifier));
    }
}