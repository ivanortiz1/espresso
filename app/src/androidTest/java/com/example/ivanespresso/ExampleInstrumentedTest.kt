package com.example.ivanespresso
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class MainActivityEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testAddTask() {
        // Introducir texto en el campo
        Espresso.onView(ViewMatchers.withId(R.id.taskInput))
            .perform(ViewActions.typeText("Nueva tarea"), ViewActions.closeSoftKeyboard())

        // Presionar el botón de añadir
        Espresso.onView(ViewMatchers.withId(R.id.addButton)).perform(ViewActions.click())

        // Verificar que la tarea aparece en la lista
        Espresso.onView(ViewMatchers.withText("Nueva tarea"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testAddMultipleTasksAndRemoveOne() {
        val taskNames = arrayOf("Task 1", "Task 2", "Task 3")

        // Añadir múltiples tareas
        for (taskName in taskNames) {
            Espresso.onView(ViewMatchers.withId(R.id.taskInput))
                .perform(ViewActions.typeText(taskName), ViewActions.closeSoftKeyboard())
            Espresso.onView(ViewMatchers.withId(R.id.addButton)).perform(ViewActions.click())
        }

        // Eliminar una tarea
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MainActivity.TaskAdapter.TaskViewHolder>(1, ViewActions.click()))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.taskNameTextView), ViewMatchers.withText("Tarea 2")))
            .check(ViewAssertions.doesNotExist())
    }

}