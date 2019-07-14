package com.ddapps.marvelstormsecapp.adapters

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.ddapps.marvelstormsecapp.data.models.Character
import com.ddapps.marvelstormsecapp.data.models.FavoriteHero
import com.ddapps.marvelstormsecapp.di.ktx.load
import com.ddapps.marvelstormsecapp.di.ktx.toTypeface
import com.ddapps.marvelstormsecapp.ui.CharacterDetailActivity
import com.ddapps.marvelstormsecapp.ui.view.BaseViewHolder
import kotlinx.android.synthetic.main.hero_item.view.*
import timber.log.Timber
import android.support.v4.util.Pair as UtilPairCompat


class CharacterViewHolder(
    itemView: View

) : BaseViewHolder<Character>(itemView) {

    override fun bind(item: Character) {
        val favoriteHero = FavoriteHero(item.id, item.name, item.description, item.thumbnail.toString())

        itemView.apply {
            hero_name.text = item.name

            character_description.text = if (item.description == null || item.description.isEmpty()) {
                context.getString(com.ddapps.marvelstormsecapp.R.string.detail_data_description_empty)
            } else {
                item.description
            }

//            Glide.with(this)
//                .load(item.thumbnail)
//                .into(object : CustomTarget<Drawable>() {
//                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
//                        hero_item_layoout.statusBarBackground = resource
//                    }
//
//
//                    override fun onLoadCleared(@Nullable placeholder: Drawable?) {
//
//                    }
//                })
            character_image.load(context as Activity, item.thumbnail.toString())

            itemView.setOnClickListener {
                Timber.e("click")
                val intent = Intent(context, CharacterDetailActivity::class.java)
                intent.putExtra(CharacterDetailActivity.EXTRA_FAVORITE_HERO, favoriteHero)

                val activity = context as Activity
                Timber.e("${item.thumbnail}")

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    UtilPairCompat.create(itemView.hero_name as View, item.name),
                    UtilPairCompat.create(itemView.character_description as View, item.description),
                    UtilPairCompat.create(itemView.character_image as View, item.thumbnail.toString())
                )

                ContextCompat.startActivity(activity, intent, options.toBundle())

            }
            hero_name.toTypeface("OpenSans-SemiBold")
            character_description.toTypeface("OpenSans-Regular")

        }
    }
}
