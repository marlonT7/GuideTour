package com.example.marlon.guidetour

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

//    private lateinit var searchView:SearchView
    private lateinit var adapter:CategoryAdapter
    private lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       viewPager = findViewById(R.id.page)

        // Create an adapter that knows which fragment should be shown on each page
        adapter = CategoryAdapter(this, supportFragmentManager)

        // Set the adapter onto the view pager
        viewPager.adapter = adapter
        // Find the tab layout that shows the tabs
        val tabLayout = findViewById<TabLayout>(R.id.tab)
        tabLayout.addTab(tab.newTab().setText(getString(R.string.restaurant)))
        tabLayout.addTab(tab.newTab().setText(getString(R.string.river)))
        tabLayout.addTab(tab.newTab().setText(getString(R.string.park)))
        tabLayout.addTab(tab.newTab().setText(getString(R.string.beach)))
        tabLayout.addTab(tab.newTab().setText(getString(R.string.lake)))


        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
//        tabLayout.setupWithViewPager(viewPager)
//        for (i in 0.. 5 ) {
//            whiteNotificationBar(((adapter.getItem(0) as PlacesListFragment).recyclerView))
//        }
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        val searchItem = menu.findItem(R.id.action_search)
//        val searchView = searchItem.actionView as SearchView
//        searchView.queryHint = "Search View Hint"
//        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String): Boolean {
//                val placesListAdapter:PlacesListAdapter = (adapter.getItem(viewPager.currentItem) as PlacesListFragment).recyclerView.adapter as PlacesListAdapter
//                placesListAdapter.filterWord(newText)
//                placesListAdapter.notifyDataSetChanged()
//                return false
//            }
//            override fun onQueryTextSubmit(query: String): Boolean {
//                val placesListAdapter:PlacesListAdapter = (adapter.getItem(viewPager.currentItem) as PlacesListFragment).recyclerView.adapter as PlacesListAdapter
//                placesListAdapter.filterWord(query)
//                placesListAdapter.notifyDataSetChanged()
//                return false
//            }
//        })
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.itemId
//        return if (id == R.id.action_search) {
//            true
//        } else super.onOptionsItemSelected(item)
//    }
//
//    override fun onBackPressed() {
//        // close search view on back button pressed
//        if (!searchView.isIconified) {
//            searchView.isIconified = true
//            return
//        }
//        super.onBackPressed()
//    }
//
//    private fun whiteNotificationBar(view: View) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            var flags = view.systemUiVisibility
//            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            view.systemUiVisibility = flags
//            window.statusBarColor = Color.WHITE
//        }
//    }

}
