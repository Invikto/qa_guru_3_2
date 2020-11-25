package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

  @Test
  @DisplayName("The form submits with valid data")
  void fillFormTest() {

    String  url = "https://demoqa.com/automation-practice-form",
            firstName = "John",
            lastName = "Smith",
            email = "john.smith@gmail.com",
            gender = "Male",
            phone = "5432167890",
            year = "2000",
            month = "July",
            date = "23",
            subjects = "English",
            address = "315 RODEO DRIVE",
            hobby = "Music",
            fileName = "qa_guru.jpeg",
            state = "Haryana",
            city = "Karnal";

    // Open the Practice Form.
    open(url);

    // Fill the form.
    $("#firstName").setValue(firstName);
    $("#lastName").setValue(lastName);
    $("#userEmail").setValue(email);
    $("#genterWrapper").$(byText(gender)).click();
    $("#userNumber").setValue(phone);
    $("#dateOfBirthInput").click();
    $("[class*='year-select']").selectOption(year);
    $("[class*='month-select']").selectOption(month);
    $(String.format("[class*='day--0%s']", date)).click();
    $("#subjectsInput").setValue(subjects).pressEnter();
    $("#currentAddress").setValue(address);
    $("#hobbiesWrapper").$(byText(hobby)).click();
    $("#uploadPicture").uploadFile(new File("./src/test/resources/images/".concat(fileName)));
    $("#state").scrollTo().click();
    $("#state").$(byText(state)).click();
    $("#city").click();
    $("#city").$(byText(city)).click();

    // Submit the form.
    $("#submit").click();

    // Verify submited date.
    $x("//td[text()='Student Name']").sibling(0).shouldHave(text(String.format("%s %s", firstName, lastName)));
    $x("//td[text()='Student Email']").sibling(0).shouldHave(text(email));
    $x("//td[text()='Gender']").sibling(0).shouldHave(text(gender));
    $x("//td[text()='Mobile']").sibling(0).shouldHave(text(phone));
    $x("//td[text()='Date of Birth']").sibling(0).shouldHave(text(String.format("%s %s,%s", date, month, year)));
    $x("//td[text()='Subjects']").sibling(0).shouldHave(text(subjects));
    $x("//td[text()='Hobbies']").sibling(0).shouldHave(text(hobby));
    $x("//td[text()='Picture']").sibling(0).shouldHave(text(fileName));
    $x("//td[text()='Address']").sibling(0).shouldHave(text(address));
    $x("//td[text()='State and City']").sibling(0).shouldHave(text(String.format("%s %s", state, city)));

  }

}
