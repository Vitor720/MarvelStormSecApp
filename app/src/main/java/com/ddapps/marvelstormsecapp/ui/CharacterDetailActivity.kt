package com.ddapps.marvelstormsecapp.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.ddapps.marvelstormsecapp.R
import com.ddapps.marvelstormsecapp.adapters.DetailDataAdapter
import com.ddapps.marvelstormsecapp.data.models.FavoriteHero
import com.ddapps.marvelstormsecapp.di.ktx.load
import com.ddapps.marvelstormsecapp.di.ktx.obtainViewModel
import com.ddapps.marvelstormsecapp.di.ktx.toTypeface
import com.ddapps.marvelstormsecapp.ui.view.ItemSpacingDecoration
import com.ddapps.marvelstormsecapp.viewmodels.CharacterDetailViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.activity_character_detail.*
import kotlinx.android.synthetic.main.content_character_detail.*
import timber.log.Timber
import javax.inject.Inject

class CharacterDetailActivity : AppCompatActivity(), HasActivityInjector {

    companion object {
        const val EXTRA_FAVORITE_HERO = "CharacterDetailActivity.EXTRA_FAVORITE_HERO"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var dispatchingAndroidJnjector: DispatchingAndroidInjector<Activity>
    private lateinit var detailViewModel: CharacterDetailViewModel
    private val comicAdapter = DetailDataAdapter()
    private val eventAdapter = DetailDataAdapter()
    private val storyAdapter = DetailDataAdapter()
    private val seriesAdapter = DetailDataAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ActivityCompat.postponeEnterTransition(this)
        Timber.plant(Timber.DebugTree())


        detailViewModel = obtainViewModel(viewModelFactory, CharacterDetailViewModel::class.java)

        Timber.i("Antes dos recyler  o CharacterDetailActivity")

        loadRecylerView(comics_recycler_view, comicAdapter)
        loadRecylerView(events_recycler_view, eventAdapter)
        loadRecylerView(stories_recycler_view, storyAdapter)
        loadRecylerView(series_recycler_view, seriesAdapter)

        setTextStyle()

        Timber.i("Completou o CharacterDetailActivity")

    }

    private fun loadRecylerView(recyler: RecyclerView, customAdapter: DetailDataAdapter) {
        recyler.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            addItemDecoration(ItemSpacingDecoration(topOffset = 10))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                ActivityCompat.finishAfterTransition(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val favoriteHero: FavoriteHero = intent.getParcelableExtra(EXTRA_FAVORITE_HERO)
        bindData(favoriteHero)

    }

    fun setTextStyle() {
        hero_description.toTypeface("OpenSans-Regular")
        comics_section_title.toTypeface("OpenSans-SemiBold")
        events_section_title.toTypeface("OpenSans-SemiBold")
        stories_section_title.toTypeface("OpenSans-SemiBold")
        series_section_title.toTypeface("OpenSans-SemiBold")
    }


    private fun bindData(favoriteHero: FavoriteHero) {
        observeCharacterDetails()
        detailViewModel.loadCharacterDetails(favoriteHero.id)

        ViewCompat.setTransitionName(hero_description, favoriteHero.description)
        ViewCompat.setTransitionName(hero_parallax_image, favoriteHero.thumbnail)
        toolbar.title = favoriteHero.name
        hero_description.text = if (favoriteHero.description == null || favoriteHero.description.isEmpty()) {
            getString(R.string.detail_data_description_empty)
        } else {
            favoriteHero.description
        }
        hero_parallax_image.load(this, favoriteHero.thumbnail, true)
    }

    private fun observeCharacterDetails() {
        detailViewModel.characterComicsLiveData.observe(this, Observer { comics ->
            comics?.let { comicAdapter.addItems(comics) }
        })

        detailViewModel.characterEventsLiveData.observe(this, Observer { events ->
            events?.let { eventAdapter.addItems(events) }
        })

        detailViewModel.characterStoriesLiveData.observe(this, Observer { stories ->
            stories?.let { storyAdapter.addItems(stories) }
        })

        detailViewModel.characterSeriesLiveData.observe(this, Observer { series ->
            series?.let { seriesAdapter.addItems(series) }
        })
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidJnjector
    }
}
