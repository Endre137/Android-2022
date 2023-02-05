package com.example.a3track.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.a3track.MyApplication
import com.example.a3track.R
import com.example.a3track.repository.TrackerRepository
import com.example.a3track.viewmodel.*
import org.w3c.dom.Text


class MyProfileFragment : Fragment() {

    private lateinit var profileName: TextView
    private lateinit var mentorName : TextView
    private lateinit var userProfilePicture: ImageView
    private lateinit var mentorProfilePicture: ImageView
    private lateinit var userRole: TextView
    private lateinit var mentorRole: TextView
    private lateinit var organization: ImageView
    private lateinit var updateProfile: ImageView
    private lateinit var profileEmail: TextView
    private lateinit var telephoneNumber: TextView
    private lateinit var location: TextView
    private lateinit var currentUserViewModel: UserViewModel
    private lateinit var allUserViewModel: AllUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factoryU = UserViewModelFactory(TrackerRepository())
        currentUserViewModel = ViewModelProvider(this, factoryU).get(UserViewModel::class.java)

        val factoryA = AllUserViewModelFactory(TrackerRepository())
        allUserViewModel = ViewModelProvider(this, factoryA).get(AllUserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        currentUserViewModel.getCurrentUser()
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allUserViewModel.getAllUsers(MyApplication.token)
        mentorName = view.findViewById(R.id.mentorName)
        mentorName.text = allUserViewModel.getUserByDepType(currentUserViewModel.getDepartmentId(),0).toString()

        profileName = view.findViewById(R.id.profileName)
        profileName.text = currentUserViewModel.getName()
        initViewItems()

//        registerListeners()
    }



    private fun initViewItems(){
//        val mentorName =requireView().findViewById(R.id.mentorName)
//        mentorName.text = allUserViewModel.getUserByDepType(currentUserViewModel.getDepartmentId(),0)

    }
}