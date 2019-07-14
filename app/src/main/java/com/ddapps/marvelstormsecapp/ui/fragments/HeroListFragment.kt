package com.ddapps.marvelstormsecapp.ui.fragments

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.VisibleForTesting
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.ddapps.marvelstormsecapp.R
import com.ddapps.marvelstormsecapp.adapters.CharacterAdapter
import com.ddapps.marvelstormsecapp.data.models.Character
import com.ddapps.marvelstormsecapp.di.Injectable
import com.ddapps.marvelstormsecapp.di.ktx.obtainViewModel
import com.ddapps.marvelstormsecapp.ui.view.ItemSpacingDecoration
import com.ddapps.marvelstormsecapp.viewmodels.HeroListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_hero_list.*
import timber.log.Timber
import javax.inject.Inject


class HeroListFragment : DaggerFragment(), Injectable {

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HeroListViewModel
    private lateinit var heroAdapter: CharacterAdapter
    private var recyclerState: Parcelable? = null

    companion object {
        fun newInstance(): HeroListFragment {
            return HeroListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_hero_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryText: String): Boolean {
                searchCharacters(queryText)
                return true
            }

            override fun onQueryTextChange(queryText: String): Boolean {
                searchCharacters(queryText)
                return true
            }
        })

        searchView.setOnCloseListener {
            heroAdapter.clear()
            hero_list_recycler.showProgress()
            viewModel.loadHeroes()
            viewModel.isSearching = true
            true
        }
    }

    private fun searchCharacters(queryText: String) {
        observeSearchResults()
        viewModel.searchHeroes(queryText)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hero_list_recycler.progressView.isShown) {
            hero_list_recycler.showProgress()
        }
        viewModel = (view.context as FragmentActivity).obtainViewModel(viewModelFactory, HeroListViewModel::class.java)
        Timber.e("Chegou aqui no onViewCreated")
        heroAdapter = CharacterAdapter()
        hero_list_recycler.apply {
            adapter = heroAdapter
            setupMoreListener(viewModel, HeroListViewModel.RESULTS_OFFSET)
            setLayoutManager(LinearLayoutManager(context))
            addItemDecoration(ItemSpacingDecoration(topOffset = 5))
        }
        initObserver()
        viewModel.loadHeroes(viewModel.currentOffset)
    }

    override fun onStop() {
        super.onStop()
        viewModel.currentOffset = 0
        heroAdapter.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("lmState", hero_list_recycler.recyclerView.layoutManager.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        recyclerState = savedInstanceState?.getParcelable("lmState")
    }

    private fun observeSearchResults() {
        val heroListSearchObserver = Observer<List<Character>> { results ->
            results?.let {

                if (recyclerState != null) {
                    hero_list_recycler.recyclerView.layoutManager.onRestoreInstanceState(recyclerState)
                }
                heroAdapter.clear()
                heroAdapter.addItems(results)
            }
        }
        viewModel.searchResultsLiveData.observe(this, heroListSearchObserver)
    }

    private fun initObserver() {
        val heroesListObserver = Observer<List<Character>> { results ->
            results?.let {

                if (recyclerState != null) {
                    hero_list_recycler.recyclerView.layoutManager.onRestoreInstanceState(recyclerState)
                }
                heroAdapter.addItems(results)
                //viewModel.isSearching = false

                if (!hero_list_recycler.recyclerView.isShown) {
                    hero_list_recycler.showRecycler()
                }
                hero_list_recycler.hideMoreProgress()
            }
        }
        viewModel.allHeroesLiveData.observe(this, heroesListObserver)
        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            errorMessage?.let { Snackbar.make(hero_list_recycler, errorMessage, Snackbar.LENGTH_SHORT).show() }
        })
    }
}