package com.sourcey.materiallogindemo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    public void signup() throws InterruptedException {
        onView(withId(R.id.link_signup)).perform(click());
        onView(withId(R.id.input_name)).perform(typeText("testName"), closeSoftKeyboard());
        onView(withId(R.id.input_address)).perform(typeText("testAddress"), closeSoftKeyboard());
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.input_mobile)).perform(typeText("0801234567"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("testPass"), closeSoftKeyboard());
        onView(withId(R.id.input_reEnterPassword)).perform(typeText("testPass"), closeSoftKeyboard());
        onView(withId(R.id.btn_signup)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.btn_logout)).perform(click());
        Thread.sleep(3000);
    }

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void LoginSuccess() throws InterruptedException {
        signup();
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("testPass"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Material Login Example")).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailValidPasswordLessThanFour() throws InterruptedException {
        signup();
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("tes"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailValidPasswordMoreThanTen() throws InterruptedException {
        signup();
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("testpassmorethan10"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailValidPasswordBlank() throws InterruptedException {
        signup();
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText(""));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailValidPasswordIncorrect() throws InterruptedException {
        signup();
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("tessPass"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailInvalidPasswordValid() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmailcom"));
        onView(withId(R.id.input_password)).perform(typeText("testPass"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailInvalidPasswordLessThanFour() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmailcom"));
        onView(withId(R.id.input_password)).perform(typeText("tes"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailInvalidPasswordMoreThanTen() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmailcom"));
        onView(withId(R.id.input_password)).perform(typeText("testpassmorethan10"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailInvalidPasswordBlank() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmailcom"));
        onView(withId(R.id.input_password)).perform(typeText(""));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailInvalidPasswordIncorrect() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText("testEmail@gmailcom"));
        onView(withId(R.id.input_password)).perform(typeText("tessPass"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailBlankPasswordValid() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText(""));
        onView(withId(R.id.input_password)).perform(typeText("testPass"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailBlankPasswordLessThanFour() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText(""));
        onView(withId(R.id.input_password)).perform(typeText("tes"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailBlankPasswordMoreThanTen() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText(""));
        onView(withId(R.id.input_password)).perform(typeText("testpassmorethan10"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailBlankPasswordBlank() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText(""));
        onView(withId(R.id.input_password)).perform(typeText(""));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void EmailBlankPasswordIncorrect() throws InterruptedException {
        onView(withId(R.id.input_email)).perform(typeText(""));
        onView(withId(R.id.input_password)).perform(typeText("tessPass"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login failed")).inRoot(withDecorView(not(activityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

}
