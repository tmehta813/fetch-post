import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.postapp.R
import com.sample.postapp.postdetails.presentation.OnClickListener
import com.sample.postapp.postdetails.presentation.PostWithComments
import com.sample.postapp.databinding.ItemPostBinding

class PostAdapter(val onClickListener: OnClickListener) :
    ListAdapter<PostWithComments, PostAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentList[position])
        }
    }

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postWithComments: PostWithComments) {
            val post = postWithComments.post
            val comments = postWithComments.comments
            val user = postWithComments.user // Newly added user object

            binding.textTitle.text = post.title
            binding.textBody.text = post.body

            binding.textComments.text = binding.root.resources.getString(R.string.comments_count, comments.size)
            if (comments.isNotEmpty()) {
                binding.textComment1.text = comments[0].name
            } else {
                binding.textComment1.text = ""
            }
            binding.textPostId.text= binding.root.resources.getString(R.string.post_id, post.id)
            if (comments.size > 1) {
                binding.textComment2.text = comments[1].name
            } else {
                binding.textComment2.text = ""
            }

            // Bind user details
            user?.let {
                binding.textUserName.text = user.name
                binding.textUserEmail.text = user.email
                binding.textUserWebsite.text = user.website
            }
        }
    }

    // this is used to do calculation on background thread. when we pass more data to list
    class DiffCallback : DiffUtil.ItemCallback<PostWithComments>() {
        override fun areItemsTheSame(
            oldItem: PostWithComments,
            newItem: PostWithComments
        ): Boolean {
            return oldItem.post.id == newItem.post.id
        }

        override fun areContentsTheSame(
            oldItem: PostWithComments,
            newItem: PostWithComments
        ): Boolean {
            return oldItem.post == newItem.post && oldItem.comments == newItem.comments && oldItem.user == newItem.user
        }
    }
}
