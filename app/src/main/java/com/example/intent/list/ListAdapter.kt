package com.example.intent.list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.intent.network.GitHubSearchModel
import com.example.intent.R
import java.util.*


class ListAdapter(
    private val context: Context,
    userList: ArrayList<GitHubSearchModel.GitHubSearchItemModel>
) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var filteredGitHubList: ArrayList<GitHubSearchModel.GitHubSearchItemModel> = userList
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.list_item,
            viewGroup,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.tvName.text = filteredGitHubList[position].fullName
        viewHolder.tvDescription.text = filteredGitHubList[position].description
        viewHolder.tvUpdated.text = filteredGitHubList[position].updatedAt
    }

    override fun getItemCount(): Int {
        return filteredGitHubList.size
    }

    inner class MyViewHolder(view: View) : ViewHolder(view) {
        val tvName: TextView = view.findViewById<View>(R.id.tvName) as TextView
        val tvDescription: TextView = view.findViewById<View>(R.id.tvDescription) as TextView
        val tvUpdated: TextView = view.findViewById<View>(R.id.tvUpdated) as TextView

        init {
            view.setOnClickListener {
                val position = adapterPosition
                val intent = Intent(Intent.ACTION_VIEW)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.data = Uri.parse(filteredGitHubList[position].htmlUrl)
                context.startActivity(intent)
            }
        }
    }
}