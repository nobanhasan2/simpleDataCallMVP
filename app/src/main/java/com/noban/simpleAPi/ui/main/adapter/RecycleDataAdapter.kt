package com.noban.simpleAPi.ui.main.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noban.simpleAPi.R
import com.noban.simpleAPi.model.RPData
import kotlinx.android.synthetic.main.photo_item.view.*

class RecycleDataAdapter(val rpData: List<RPData>) : RecyclerView.Adapter<RecycleDataAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.photo_item, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
       return rpData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = rpData.get(position)
        holder.bind(photo)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: RPData){
            itemView.tv_title.text = photo.title.toString()

                itemView.setOnClickListener {

                }
        }

    }


}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
