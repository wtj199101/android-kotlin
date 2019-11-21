package com.example.complextest.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.complextest.R
import com.example.complextest.adapter.PlanetGridAdapter
import com.example.complextest.model.Planet
import kotlinx.android.synthetic.main.activity_grid_view.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.selector

class GridViewActivity : AppCompatActivity(){
    private val dividers = listOf(
            "不显示分隔线",
            "只显示内部分隔线(NO_STRETCH)",
            "只显示内部分隔线(COLUMN_WIDTH)",
            "只显示内部分隔线(STRETCH_SPACING)",
            "只显示内部分隔线(SPACING_UNIFORM)",
            "显示全部分隔线(看我用padding大法)")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)
        val adapter = PlanetGridAdapter(this, Planet.defualtList, Color.WHITE)
        gv_planet.adapter=adapter
        gv_planet.onItemClickListener=adapter
        gv_planet.onItemLongClickListener=adapter
        val dividerPad = dip(2)
        sp_grid.visibility= View.GONE
        tv_spinner.visibility= View.VISIBLE
        tv_spinner.text=dividers[0]

        tv_spinner.setOnClickListener {
            selector("请选择分隔线显示方式",dividers){  i: Int ->
                tv_spinner.text = dividers[i]
                gv_planet.setBackgroundColor(Color.RED)
                gv_planet.horizontalSpacing = dividerPad
                gv_planet.verticalSpacing = dividerPad
                gv_planet.stretchMode = GridView.STRETCH_COLUMN_WIDTH
                gv_planet.columnWidth = 250
                gv_planet.setPadding(0, 0, 0, 0)
                when (i) {
                    0 -> {
                        gv_planet.setBackgroundColor(Color.WHITE)
                        gv_planet.horizontalSpacing = 0
                        gv_planet.verticalSpacing = 0
                    }
                    1 -> gv_planet.stretchMode = GridView.NO_STRETCH
                    2 -> gv_planet.stretchMode = GridView.STRETCH_COLUMN_WIDTH
                    3 -> gv_planet.stretchMode = GridView.STRETCH_SPACING
                    4 -> gv_planet.stretchMode = GridView.STRETCH_SPACING_UNIFORM
                    5 -> gv_planet.setPadding(dividerPad, dividerPad, dividerPad, dividerPad)
                }

            }
        }

    }
}