package com.ddapps.marvelstormsecapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.view.Window
import com.ddapps.marvelstormsecapp.ui.fragments.HeroListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setAnimations()
        Timber.plant(Timber.DebugTree())

        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.black)))


        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_content, HeroListFragment.newInstance())
            .commit()

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    private fun setAnimations() {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Explode()
        window.allowEnterTransitionOverlap = true
        window.allowReturnTransitionOverlap = true
    }
}
