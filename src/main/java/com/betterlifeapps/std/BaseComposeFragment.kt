package com.betterlifeapps.std

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

abstract class BaseComposeFragment : BaseFragment(0) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val composeView = inflater.inflate(R.layout.fragment_base_compose, container, false)
        (composeView as ComposeView).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                val fragmentManager = parentFragmentManager
                CompositionLocalProvider(
                    LocalFragmentManager provides fragmentManager,
                    LocalNavController provides rememberNavController()
                ) {
                    View()
                }
            }
        }
        return composeView
    }

    @Composable
    abstract fun View()
}

val LocalFragmentManager = staticCompositionLocalOf<FragmentManager?> { null }
val LocalNavController = staticCompositionLocalOf<NavHostController?> { null }