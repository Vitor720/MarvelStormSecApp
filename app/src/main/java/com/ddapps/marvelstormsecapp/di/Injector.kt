package com.ddapps.marvelstormsecapp.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

import com.ddapps.marvelstormsecapp.PusherAppplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector


object Injector {

    fun init(application: PusherAppplication) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is HasSupportFragmentInjector && activity is Injectable) {
                    AndroidInjection.inject(activity)
                }
                if (activity is FragmentActivity) {
                    activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentPreAttached(
                                fm: FragmentManager,
                                fragment: Fragment,
                                context: Context
                            ) {
                                if (fragment is Injectable) {
                                    AndroidSupportInjection.inject(fragment)
                                }
                            }
                        }, true
                    )
                }
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }
}