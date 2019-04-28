package com.zpi.xmas2019

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.api.Distribution
import com.zpi.xmas2019.adapter.GalleryImageAdapter
import com.zpi.xmas2019.adapter.StallImageAdapter
import com.zpi.xmas2019.adapter.StallRecycleViewAdapter
import kotlinx.android.synthetic.main.fragment_stall_details.*
import kotlinx.android.synthetic.main.fragment_stall_details.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STALL_NR = "stall_number"
private const val TAGS = "tags"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [StallDetails.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [StallDetails.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class StallDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var stallnr: Int? = null
    private var tags: ArrayList<String>? = null
    private var listener: OnFragmentInteractionListener? = null
    private var images: MutableList<String> = MutableList<String>(3){index -> "stall" + index + ".jpg"}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("INFO", "Creating StallDetails Fragment")
        arguments?.let {
            stallnr = it.getInt(STALL_NR)
            Log.i("INFO", "Stall_nr: $stallnr")
            tags = it.getStringArrayList(TAGS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("INFO", "Creating View StallDetails Fragment")
        var title = "Domek nr $stallnr"
        activity?.title = title
        // Inflate the layout for this fragment
        var viewOfLayout = inflater.inflate(R.layout.fragment_stall_details, container, false)

        viewOfLayout.stall_title.text = title
        Log.i("INFO", "Title set")
        val viewPager = viewOfLayout.stallViewPager
        val viewPagerAdapter = StallImageAdapter(activity as Activity, images)
        viewPager.adapter = viewPagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                updateDots(position)
            }

        })

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val recyclerView = viewOfLayout.tags_container
        recyclerView.layoutManager = layoutManager
        val recyclerViewAdapter = StallRecycleViewAdapter(tags)
        recyclerView.adapter = recyclerViewAdapter

        return viewOfLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateDots(0);
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("INFO", "Attaching StallDetails Fragment")
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun updateDots(currentDot: Int){
        if(dotsContainer.childCount>0){
            dotsContainer.removeAllViews();
        }

        Log.i("INFO", "cleaned container")

        var dots: Array<ImageView?> = arrayOfNulls<ImageView>(images.size)


        for(i in 0..images.size-1) {

            dots[i] = ImageView(context)

            if (i == currentDot) {
                dots[i]?.setImageDrawable(context?.getDrawable(R.drawable.active_dot))
            } else dots[i]?.setImageDrawable(context?.getDrawable(R.drawable.inactive_dot))


            var layoutParams =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(8, 0, 8, 0)
            dotsContainer.addView(dots[i], layoutParams)
            dotsContainer.bringToFront()
        }
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StallDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(stallnr: Int, tags: ArrayList<String>) =
            StallDetails().apply {
                arguments = Bundle().apply {
                    putInt(STALL_NR, stallnr)
                    putStringArrayList(TAGS, tags)
                }
            }
    }
}
