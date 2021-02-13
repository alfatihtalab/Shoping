package com.alfatih.shoping.home

import androidx.fragment.app.Fragment
import com.alfatih.shoping.main.SignInFragment
import com.google.android.play.core.splitinstall.c

open class FragmentTest {

    var fragmentCollection = mutableMapOf<Int, Fragment>(
        1 to HomeScreenFragment(),
        2 to SignInFragment()
    )






}
fun main(){
    val lambda : (Int , Int) -> Int = {a: Int , b:Int -> a+b }
    print(lambda(5,6))
}