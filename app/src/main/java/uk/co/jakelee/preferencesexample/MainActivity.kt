package uk.co.jakelee.preferencesexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_frame, PrefsFragment())
            .commit()
    }
}