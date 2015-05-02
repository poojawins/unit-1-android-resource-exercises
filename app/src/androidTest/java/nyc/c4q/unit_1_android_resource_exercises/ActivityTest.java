package nyc.c4q.unit_1_android_resource_exercises;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.w3c.dom.Text;

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
        Resources resources = mActivity.getResources();

        int helloWorldIdentifier = resources.getIdentifier("hello_world", "string", mActivity.getPackageName());
        int imSorryDaveIdentifier = resources.getIdentifier("im_sorry_dave", "string", mActivity.getPackageName());

        System.out.println(helloWorldIdentifier);

        if (helloWorldIdentifier > 0) {
            Assert.assertEquals("Hello world!", resources.getString(helloWorldIdentifier));
        } else {
            fail("hello_world not found");
        }

        if (imSorryDaveIdentifier > 0) {
            Assert.assertEquals("I'm sorry Dave, I'm afraid I can't let you do that.",
                    resources.getString(imSorryDaveIdentifier));
        } else {
            fail("im_sorry_dave not found");
        }
    }

    // TODO internationalization

    @SuppressWarnings("deprecated")
    public void testDrawableRed() {
        // make the "res/drawable" folder, then
        // put "hello_red.png" in it

        Resources resources = mActivity.getResources();

        int drawableRedIdentifier = resources.getIdentifier("hello_red", "drawable", mActivity.getPackageName());

        assertTrue("hello_red.png was not found", drawableRedIdentifier > 0);

        Bitmap bm;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            bm = ((BitmapDrawable) resources.getDrawable(drawableRedIdentifier)).getBitmap();
        } else {
            bm = ((BitmapDrawable) resources.getDrawable(drawableRedIdentifier, null)).getBitmap();
        }

        Assert.assertEquals("actual height value: " + bm.getHeight(), 40, bm.getHeight());
        Assert.assertEquals("actual width value: " + bm.getWidth(), 40, bm.getWidth());
        Assert.assertEquals(0xFFFF0000, bm.getPixel(0, 0));
    }

    public void testDrawableBlue() {
        // make the "res/drawable" folder, then
        // put "hello_blue.png" in it

        Resources resources = mActivity.getResources();

        int drawableBlueIdentifier = resources.getIdentifier("hello_blue", "drawable", mActivity.getPackageName());

        assertTrue("hello_blue.png was not found", drawableBlueIdentifier > 0);

        Bitmap bm;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            bm = ((BitmapDrawable) resources.getDrawable(drawableBlueIdentifier)).getBitmap();
        } else {
            bm = ((BitmapDrawable) resources.getDrawable(drawableBlueIdentifier, null)).getBitmap();
        }

        Assert.assertEquals(40, bm.getHeight());
        Assert.assertEquals(40, bm.getWidth());
        Assert.assertEquals(0xFF0000FF, bm.getPixel(0, 0));

    }

    public void testParentLayout() {
        int parentLayoutId = mActivity.getResources().getIdentifier("exercise_parent_layout", "id", mActivity.getPackageName());

        assertTrue("Parent Layout not found", parentLayoutId > 0);
        View parentLayout = mActivity.findViewById(parentLayoutId);
        assertTrue("Parent Layout not a LinearLayout", parentLayout instanceof LinearLayout);
    }

    public void testEditText() {
        int editTextId = mActivity.getResources().getIdentifier("exercise_edit_text_input", "id", mActivity.getPackageName());

        assertTrue("EditText ID not found", editTextId > 0);

        EditText et = (EditText) mActivity.findViewById(editTextId);

        assertNotNull("EditText was not found", et);

        assertEquals(WindowManager.LayoutParams.MATCH_PARENT, et.getLayoutParams().height);
        assertEquals(WindowManager.LayoutParams.MATCH_PARENT, et.getLayoutParams().width);
    }

    public void testButton() {
        int buttonId = mActivity.getResources().getIdentifier("exercise_button", "id", mActivity.getPackageName());

        assertTrue("Button ID not found", buttonId > 0);

        Button button = (Button) mActivity.findViewById(buttonId);

        assertNotNull("Button was not found", button);

        assertEquals(WindowManager.LayoutParams.MATCH_PARENT, button.getLayoutParams().height);
        assertEquals(WindowManager.LayoutParams.MATCH_PARENT, button.getLayoutParams().width);
        assertEquals("OK", button.getText());
    }

    public void testTextView() {
        int textViewId = mActivity.getResources().getIdentifier("exercise_text_view_grade", "id", mActivity.getPackageName());

        assertTrue("TextView ID not found", textViewId > 0);

        TextView tv = (TextView) mActivity.findViewById(textViewId);

        assertNotNull("TextView was not found", tv);

        assertEquals(WindowManager.LayoutParams.WRAP_CONTENT, tv.getLayoutParams().height);
        assertEquals(WindowManager.LayoutParams.WRAP_CONTENT, tv.getLayoutParams().width);
        assertEquals("N/A", tv.getText());
    }

    public void testGradeCalculator() {
        // Make sure the grade calculator works correctly
        // 100   -> A+
        // 90-99 -> A
        // 80-89 -> B
        // 70-79 -> C
        // 65-69 -> D
        // 0-64  -> F

        int editTextId = mActivity.getResources().getIdentifier("exercise_edit_text_input", "id", mActivity.getPackageName());
        int buttonId = mActivity.getResources().getIdentifier("exercise_button", "id", mActivity.getPackageName());
        int textViewId = mActivity.getResources().getIdentifier("exercise_text_view_grade", "id", mActivity.getPackageName());

        assertTrue("EditText ID not found", editTextId > 0);
        assertTrue("Button ID not found", buttonId > 0);
        assertTrue("TextView ID not found", textViewId > 0);

        EditText et = (EditText) mActivity.findViewById(editTextId);
        Button button = (Button) mActivity.findViewById(buttonId);
        TextView tv = (TextView) mActivity.findViewById(textViewId);

        assertNotNull("EditText was not found", et);
        assertNotNull("Button was not found", button);
        assertNotNull("TextView was not found", tv);

        et.setText("59");
        button.callOnClick();
        assertEquals("F", tv.getText());

        // et.fill("68")
        // button.clickView()
        // assertEquals("D", tv.getText());

        // et.fill("75")
        // button.clickView()
        // assertEquals("C", tv.getText());

        // et.fill("86")
        // button.clickView()
        // assertEquals("B", tv.getText());

        // et.fill("95")
        // button.clickView()
        // assertEquals("A", tv.getText());

        // et.fill("100")
        // button.clickView()
        // assertEquals("A+", tv.getText());

    }

    public void testLayoutDensity() {
        // hdpi
        // mdpi
        // ldpi (low density?)
    }
}