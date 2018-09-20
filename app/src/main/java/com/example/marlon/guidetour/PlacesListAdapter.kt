package com.example.marlon.guidetour

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.place_card.view.*


class PlacesListAdapter(private var places: ArrayList<Place>, private val selectedPlace: SelectedPlace) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Interface to work in the fragment
    interface SelectedPlace {
        fun openWebSite(webSite: String)
        fun dialNumber(phone: String)
        fun expand(position: Int)
    }
//    var allPlaces = places
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var webSite: TextView
        private lateinit var phone: TextView
        private lateinit var expand: ConstraintLayout
        private lateinit var detail: ConstraintLayout
        private lateinit var category: TextView


        fun bind() {
            webSite = itemView.findViewById(R.id.place_web_site)
            phone = itemView.findViewById(R.id.place_phone_number)
            expand = itemView.findViewById(R.id.min_info)
            webSite.setOnClickListener { selectedPlace.openWebSite(webSite = webSite.text.toString()) }
            phone.setOnClickListener { selectedPlace.dialNumber(phone = phone.text.toString()) }
            expand.setOnClickListener { expandDetails() }
        }

        private fun expandDetails() {
            detail = itemView.findViewById(R.id.details)
            category = itemView.findViewById(R.id.place_category)
            if (detail.visibility == View.VISIBLE) {
                detail.visibility = View.GONE

            } else {
                detail.visibility = View.VISIBLE

            }
            selectedPlace.expand(adapterPosition)
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PlacesListAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_card, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(view = view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // - get element from your dataSet at this position
        // - replace the contents of the view with that element
        if (holder is MyViewHolder) {
            holder.bind()
        }
        bindView(holder, position)
    }

    // Set values to the view
    private fun bindView(holder: RecyclerView.ViewHolder, position: Int): RecyclerView.ViewHolder {
        val name: TextView = holder.itemView.place_name
        val photo: ImageView = holder.itemView.photo
        val description: TextView = holder.itemView.place_description
        val webSite: TextView = holder.itemView.place_web_site
        val phone: TextView = holder.itemView.place_phone_number
        val category: TextView = holder.itemView.place_category
        val place: Place = places[position]
        val expand: ConstraintLayout = holder.itemView.details

        // Set the places values to the view
        name.text = place.name
        photo.setImageResource(place.image)
        description.text = place.description
        webSite.text = place.webPage
        phone.text = place.phone
        category.text = place.category
        if (place.isExpanded) {
            expand.visibility = View.VISIBLE
        } else {
            expand.visibility = View.GONE
        }
        return holder
    }

    // Filters by the search param
//    fun filterWord(query: String) {
//        if (query == "") {
//            places = allPlaces
//        } else {
//            places.filter { place: Place ->
//                place.category.contains(query)
//            } as ArrayList<Place>
//        }}

        // Return the size of your dataSet (invoked by the layout manager)
        override fun getItemCount() = places.size
    }