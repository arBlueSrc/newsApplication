package com.example.global.modules.shared

object GenderList {
    fun list(): ArrayList<ItemBottomSheet> {
        val listGender: ArrayList<ItemBottomSheet> = arrayListOf()
        val genderMan = ItemBottomSheet(1, "مرد")
        val genderWoman = ItemBottomSheet(2, "زن")
        listGender.add(genderMan)
        listGender.add(genderWoman)
        return listGender
    }
}