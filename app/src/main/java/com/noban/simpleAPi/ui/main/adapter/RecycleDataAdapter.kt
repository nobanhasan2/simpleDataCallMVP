package com.noban.simpleAPi.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.noban.simpleAPi.R
import com.noban.simpleAPi.model.RPData
import com.noban.simpleAPi.ui.details.DetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_item.view.*


class RecycleDataAdapter(val rpData: List<RPData>,val context: Context) : RecyclerView.Adapter<RecycleDataAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.photo_item, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
       return rpData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = rpData.get(position)
        Picasso.get().load(photo.url).into(holder.itemView.iv_image)
        holder.bind(photo,context)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: RPData,context: Context){
            itemView.tv_title.text = photo.title.toString()
      Log.e("URL",photo.url)



            itemView.setOnClickListener {
                        val intent = Intent(it.context,DetailsActivity::class.java)
                        intent.putExtra("URL",photo.url)
                        intent.putExtra("TITLE",photo.title)
                        it.context.startActivity(intent)
                }


        }

    }


}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
