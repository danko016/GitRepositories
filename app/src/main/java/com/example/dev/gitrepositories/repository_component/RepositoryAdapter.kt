package com.example.dev.gitrepositories.repository_component

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dev.gitrepositories.R
import com.example.dev.gitrepositories.models.Repository
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by dev on 12.11.16..
 */
class RepositoryAdapter
@Inject
constructor(val context: Context, val repoList: MutableList<Repository>): RecyclerView.Adapter<RepositoryAdapter.MyViewHolder>() {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repositories = repoList[position]
        holder.tvName.text = repositories.name
        holder.tvAuthor.text = repositories.fullName
        holder.tvWatchers.text = repositories.watchers.toString()
        holder.tvFork.text = repositories.forks.toString()
        holder.tvIssues.text = repositories.openIssues.toString()


        val url = repositories.owner?.avatarUrl
        Glide.with(context).load(url).into(holder.ivImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder? {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.repository_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View?) {
            EventBus.getDefault().post(EventOpenRepository(adapterPosition))
        }

        var tvName: TextView = itemView?.findViewById<TextView>(R.id.TVName) as TextView
        var tvAuthor: TextView = itemView?.findViewById<TextView>(R.id.TVAuthor) as TextView
        var tvWatchers: TextView = itemView?.findViewById<TextView>(R.id.TVWatchers) as TextView
        var tvFork: TextView = itemView?.findViewById<TextView>(R.id.TVFork) as TextView
        var tvIssues: TextView = itemView?.findViewById<TextView>(R.id.TVIssue) as TextView
        var ivImage: ImageView = itemView?.findViewById<ImageView>(R.id.IVImage) as ImageView



        init {
            itemView?.setOnClickListener(this)
            ivImage.setOnClickListener {
                EventBus.getDefault().post(EventOpenUser(adapterPosition))
            }
        }
    }

    class EventOpenRepository(val position: Int)

    class EventOpenUser(val position: Int)
}


