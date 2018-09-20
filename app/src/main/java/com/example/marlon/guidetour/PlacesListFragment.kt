package com.example.marlon.guidetour


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "category"
private const val ARG_PARAM2 = "places"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlacesListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlacesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlacesListFragment : Fragment(), PlacesListAdapter.SelectedPlace {
    override fun expand(position: Int) {

        places[position].isExpanded = !places[position].isExpanded
    }

    // Dial the place phone number in an intent
    override fun dialNumber(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        startActivity(intent)
    }

    // Open the web site in an intent
    override fun openWebSite(webSite: String) {
        val intent = Intent(this.context, WebActivity::class.java)
        intent.data = Uri.parse(webSite)
        startActivity(intent)
    }


    private lateinit var viewManager: RecyclerView.LayoutManager

    private var category = this.arguments?.getInt(ARG_PARAM1) ?: 0


    private val categories: List<String> = listOf("Restaurant", "River", "Park", "Beach", "Lake")
    // Places registered list in the app
    private var allPlaces: ArrayList<Place> = arrayListOf(
            Place(name = "Cerro verde",
                    image = (R.drawable.cerro_verde),
                    phone = "72947255",
                    category = "Park",
                    description = "It is a beautiful park of pleasant temperature, which has very nice trails, shaded by the most diverse species of plants and trees.",
                    webPage = "http://www.mitur.gob.sv/travel/parque-natural-cerro-verde/"),
            Place(name = "Furesa",
                    image = (R.drawable.furesa),
                    webPage = "https://www.furesa.com.sv",
                    description = "It is the only refuge of wildlife of El Salvador, home to more than one dozen Tigers, Black Panthers, Lions, toucans, turtles, hippos, monkeys, spider monkeys, and recently, a Python snake. ",
                    category = "Park",
                    phone = "23479300"),
            Place(name = "Costa del Sol",
                    category = "Beach",
                    description = "It is one of the largest tourist attraction in El Salvador, with many hotels and beach clubs. The beach is visited by a large number of tourists for its beautiful beaches, sunsets, the cuisine with fresh seafood and aquatic sports. ",
                    webPage = "http://www.mitur.gob.sv/travel/parque-acuatico-costa-del-sol-2/",
                    phone = "22228000",
                    image = (R.drawable.costa_del_sol)),
            Place(name = "Apulo",
                    category = "Lake",
                    description = "Apulo means in náhuatl: \"Place which sinks in water\". This site is located in the Cantón Dolores Apulo. \n The Lake of Ilopango of 72 Km.2, is among the departments of San Salvador, Cuscatlan and La Paz. In this area the flora is classified as forest deciduous, since existing specimens are typical of this vegetable formation.",
                    webPage = "http://www.istu.gob.sv/temas/parques-acuaticos/parque-acuatico-apulo.html",
                    phone = "",
                    image = (R.drawable.apulo)),
            Place(name = "Río Lempa",
                    category = "River",
                    description = "Being the river more important of El Salvador, provides the salvadorelos, work, fun and freshness, crossing the country completely, having a few trails more beautiful than others, so how on other occasions we have mentioned areas as \"The throat of the Lempa River\", as in the picture we add how representative, many people say that it does not correspond to El Salvador, by how beautiful that is, However, it should be noted that this shot was made in the area of \u200B\u200BCítala Chalatenango, where the Lempa river passes through and lets you see this scene really impressive.",
                    webPage = "",
                    phone = "89683453",
                    image = (R.drawable.lempa)),
            Place(name = "Río Guajoyo",
                    category = "River",
                    description = "Located in the town of Metapán, Santa Ana Department, at the height of the km 100, you'll come across this beautiful landscape, being one of the rivers that throughout the year allow the practice of extreme sports, because that is a very clear flow which is contemplated. ",
                    webPage = "",
                    phone = "86240000",
                    image = (R.drawable.guajoyo)),
            Place(name = "Río Sapo",
                    category = "River",
                    description = "Located in Morazán, also allows visitors to enjoy a good dip in its waters, perhaps not as plentiful, but fresh and crystal clear, This is found Highway to Perkin, is a beautiful combination, when we combine the path of peace and conclude by visiting this beautiful place. ",
                    webPage = "",
                    phone = "67903464",
                    image = (R.drawable.sapo)),
            Place(name = "Río Sumpul",
                    category = "River",
                    description = "The idea to plunge into a river, may not be what most appeals to visitors of the Sumpul river, due to its cold temperatures, this another River in the Department of chalatenango, near El Pital, tends to be very visited by people seeking freshness, although it has a rich history, many enjoy greatly beautiful landscape that gives them and the refreshing waters. ",
                    webPage = "",
                    phone = "97658437",
                    image = (R.drawable.sumpul)),
            Place(name = "Lake Coatepeque",
                    category = "Lake",
                    description = "Coatepeque is a volcanic lake situated about an hour from San Salvador. It is much older than Ilopango, though created by a similar catastrophic eruption some five to six thousand years ago. Named Lago de Coatepeque, it is on the eastern slope of the Santa Ana volcano. It is a beautiful, clean, and sparkling blue crater-shaped lake, 6 kms wide and 120 meters deep and surrounded by steep green slopes rising up 250 to 500 meters. The scenic road between the Interamerican Highway and CA8, known as the \"panoramic highway\" for its marvelous views, curls along the steep ridge of the crater giving you glimpses of the broad fertile valleys on one side and the lake on the other.",
                    webPage = "https://www.tripadvisor.com/ShowUserReviews-g7806254-d555047-r14129772-Lake_Coatepeque-Coatepeque_Santa_Ana_Department.html",
                    phone = "58788235",
                    image = (R.drawable.coatepeque)),
            Place(name = "El Boqueron",
                    category = "Park",
                    description = "Combine the historic sights of San Salvador and the natural wonders of the El Boquerón National Park with this great-value 4-hour combo tour. Enjoy the undivided attention of your guide as you admire the remnants of San Salvador’s colonial rule and view architectural landmarks like the El Rosario Church and the Metropolitan Cathedral. Next, climb the 5,905-foot (1,800 m) peak of El Boquerón, the city’s famous volcanic crater, and enjoy a scenic hike around the rim, looking out over the city below.",
                    webPage = "https://www.tripadvisor.com/AttractionProductDetail-g294476-d11454448-San_Salvador_Combo_Tour_City_Sightseeing_and_El_Boqueron_National_Park-San_Salvador_San_Salvador_Department.html",
                    phone = "87998235",
                    image = (R.drawable.boqueron)),
            Place(name = "Clavo y Canela",
                    category = "Restaurant",
                    description = "A restaurant",
                    webPage = "http://www.clavoycanela.com.sv/",
                    phone = "25000530",
                    image = (R.drawable.clavo_canela)),
            Place(name = "La Pampa Argentina",
                    category = "Restaurant",
                    description = "A restaurant",
                    webPage = "",
                    phone = "24061001",
                    image = (R.drawable.la_pampa_argentina)),
            Place(name = "Las Brumas Grill Cafe",
                    category = "Restaurant",
                    description = "A restaurant",
                    webPage = "",
                    phone = "25080454",
                    image = (R.drawable.las_brumas_grill_cafe)),
            Place(name = "NAU Lounge",
                    category = "Restaurant",
                    description = "Authentic Japanese cuisine in a vibrant atmosphere accompany by a wide variety of cocktails and live music",
                    webPage = "https://www.ihg.com/intercontinental/hotels/gb/en/san-salvador/sslhb/hoteldetail#eatanddrink?utm_source=tripadvisor&utm_medium=referral",
                    phone = "22113333",
                    image = (R.drawable.nau_lounge)),
            Place(name = "Picasso Stone Flame Oven",
                    category = "Restaurant",
                    description = "A restaurant",
                    webPage = "https://www.ihg.com/intercontinental/hotels/gb/en/san-salvador/sslhb/hoteldetail#eatanddrink?utm_source=tripadvisor&utm_medium=referral",
                    phone = "22113333",
                    image = (R.drawable.picasso_stone_flame_oven)),
            Place(name = "Hacienda Real El Salvador",
                    category = "Restaurant",
                    description = "A restaurant",
                    webPage = "https://www.facebook.com/HaciendaRealElSavador/?utm_source=tripadvisor&utm_medium=referral",
                    phone = "22438797",
                    image = (R.drawable.hacienda_real)),
            Place(name = "El Imposible National Park",
                    category = "Park",
                    description = "Discover the unearthly landscapes and natural splendor of El Imposible National Park on a full-day private tour from San Salvador. Accompanied by a knowledgeable guide, explore the verdant wonders of this Eden-like expanse in search of exotic birds and dramatic gorges. Discover why the park is steeped in local legend and ancient mystery as you follow dense, forested trails and wade in the refreshing waters of rivers. Enjoy the undivided attention of your guide on this private tour, the itinerary and commentary of which can be tailored according to your interesting.",
                    webPage = "",
                    phone = "6794097",
                    image = (R.drawable.imposible)),
            Place(name = "Puerta del Diablo",
                    category = "Park",
                    description = "A touristic place",
                    webPage = "http://www.elsalvadorturismo.com.sv/turismoelsalvador/areasnaturales/la-puerta-del-diablo/",
                    phone = "66600666",
                    image = (R.drawable.puerta_diablo)),
            Place(name = "El Pital",
                    category = "Park",
                    description = "Is a mountain in Central America, on the border of El Salvador and Honduras. It is located 12 km (7 mi) from the town of La Palma at a height of 2,730 m (8,957 ft) above sea level, and is the highest point in Salvadoran territory. Cerro El Pital is in the middle of a cloud forest that has an average annual temperature of 10 °C (50 °F).",
                    webPage = "https://en.wikipedia.org/wiki/Cerro_El_Pital",
                    phone = "56907656",
                    image = (R.drawable.pital)),
            Place(name = "El Tunco",
                    category = "Beach",
                    description = "Ride a wave of relaxation through El Salvador’s beach area of Playa El Tunco, during this 6-hour exploration of one of the country’s top surfing spots. Enjoy the sun, sand and surf at Playa El Tunco, observing the local surfers at play. You can even choose to jump in the water for a lesson of your own. Then visit a beach club for great ocean views, a pool and an included seafood lunch. Finish your day with a trip to Fisherman’s Wharf at La Libertad. Your beach trip also covers a guide and round-trip hotel transport.",
                    webPage = "https://www.viator.com/tours/La-Libertad/El-Tunco-Village-and-Beach-from-La-Libertad/d22202-8067P13?mcid=56757",
                    phone = "65824309",
                    image = (R.drawable.tunco)),
            Place(name = "El Sunzal",
                    category = "Beach",
                    description = "A beach",
                    webPage = "",
                    phone = "92539095",
                    image = (R.drawable.sunzal)),
            Place(name = "El Majahual",
                    category = "Beach",
                    description = "El Majahual beach is one of the most visited in El Salvador, especially in the holiday season, is located in the Department of La Libertad. Since it is located in an area very close to the capital (at 34 kilometers from San Salvador), people visit this place to enjoy its friendly waves that allows you to enjoy the warm waters open to public.",
                    webPage = "http://www.elsalvadortips.com/el-majahual-beach",
                    phone = "63980345",
                    image = (R.drawable.majahual)),
            Place(name = "Barra de Santiago",
                    category = "Beach",
                    description = "La Barra de Santiago is located inside a unique ecosystem with marvelous pacific ocean beaches and the beautiful estuary, that has an extension of mangroves known as 'Pacifico Norte Centro Americano' due to the high importance of the aquatic and terrestrial biodiversity of birds, reptiles and mammals declare as wetland of international importance in july of 2014.",
                    webPage = "http://www.elsalvadortips.com/el-majahual-beach",
                    phone = "63980345",
                    image = (R.drawable.barra_santiago)),
            Place(name = "El Espino",
                    category = "Beach",
                    description = "One of the most beautiful and popular of El Salvador beaches lies 36 kilometers from the city of Usulután. Beach El Espino is formed by a physical delta, which allows tourists to walk by several meters sea in without let the water reach the knees.",
                    webPage = "http://www.elsalvadortips.com/el-espino-beach-usulutan",
                    phone = "63980345",
                    image = (R.drawable.espino)),
            Place(name = "Suchitlán",
                    category = "Lake",
                    description = "This artificial lake formed in the winter of 1976, at the end of the construction of the hydroelectric Central of Cerrón Grande; which owes its name to the estate which was located on the banks of the same on the Lempa River, is the largest lake in El Salvador extension exceeds 120 km²",
                    webPage = "",
                    phone = "97897795",
                    image = (R.drawable.suchitlan)),
            Place(name = "Güija",
                    category = "Lake",
                    description = "With an area of 44 square kilometers, this lake is shared with Guatemala, although El Salvador corresponds to 70 percent. This site has two important attractions: on the one hand its mirror of water and on the other the petroglyphs made by Indians.",
                    webPage = "",
                    phone = "26854867",
                    image = (R.drawable.guija)),
            Place(name = "Jardin de Celeste",
                    category = "Restaurant",
                    description = "Is a restaurant",
                    webPage = "http://www.eljardindeceleste.com/el-jard%C3%ADn-de-celeste%2c-un-lugar-m%C3%A1gico.--restaurante.html?utm_source=tripadvisor&utm_medium=referral",
                    phone = "24330277",
                    image = (R.drawable.garden)),
            Place(name = "La Gastroteca",
                    category = "Restaurant",
                    description = "Is a restaurant",
                    webPage = "https://lagastroteca.com.sv/?utm_source=tripadvisor&utm_medium=referral",
                    phone = "22985876",
                    image = (R.drawable.gastroteca)),
            Place(name = "Laca Laca",
                    category = "Restaurant",
                    description = "Is a restaurant",
                    webPage = "http://www.laca-laca.com/?utm_source=tripadvisor&utm_medium=referral",
                    phone = "2210 3621",
                    image = (R.drawable.laca_laca)),
            Place(name = "Tipicos Margoth",
                    category = "Restaurant",
                    description = "Is a restaurant",
                    webPage = "http://www.tipicosmargoth.com/?utm_source=tripadvisor&utm_medium=referral",
                    phone = "2210 3621",
                    image = (R.drawable.tipicos_margoth))

    )
    // Places registered list in the app by category
    private var places: ArrayList<Place> = arrayListOf()
    private lateinit var viewAdapter: PlacesListAdapter

    private lateinit var recyclerView: RecyclerView

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getInt(ARG_PARAM1)
            places = if (it.getParcelableArrayList<Place>(ARG_PARAM2) != null) {
                it.getParcelableArrayList(ARG_PARAM2)
            } else {
                allPlaces.filter { place: Place ->
                    place.category == categories[category]
                } as ArrayList<Place>
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_places_list, container, false)
        viewManager = LinearLayoutManager(this.context)
        // Sets data to the recycler view
        viewAdapter = PlacesListAdapter(places, this)
        // Divides the data in categories and send to the corresponding view page
        recyclerView = view.findViewById<RecyclerView>(R.id.places_list_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener


    // Filters by the search param
    fun filterWord(query: String) {
        places = if (query == "") {
            allPlaces.filter { place: Place ->
                place.category == categories[category]
            } as ArrayList<Place>
        } else {
            places.filter { place: Place ->
                place.category.toLowerCase().contains(query.toLowerCase())
            } as ArrayList<Place>
        }

        viewAdapter=PlacesListAdapter(places, this)

        viewAdapter.notifyDataSetChanged()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment PlacesListFragment.
         */

        @JvmStatic
        fun newInstance() =
                PlacesListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, category)
                        putParcelableArrayList(ARG_PARAM2, places)
                    }
                }
    }
}
