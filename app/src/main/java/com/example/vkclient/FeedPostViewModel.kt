package com.example.vkclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkclient.domain.FeedPost
import com.example.vkclient.domain.StatisticPostItem
import com.example.vkclient.ui.theme.FeedPostScreenState

/**
 * View model для постов.
 *
 * @constructor Create empty Feed post view model
 */
class FeedPostViewModel : ViewModel() {

    // Список постов TODO сделать получение даннных из сети.
    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(13) {
            add(FeedPost(id = it))
        }
    }

    // Начаьный state для постов.
    private val initialState = FeedPostScreenState.Posts(posts = sourceList)

    // State для поста
    private val _screenState = MutableLiveData<FeedPostScreenState>(initialState)
    val screenState: LiveData<FeedPostScreenState> = _screenState

    /**
     * Изменение значений для элемента статистика поста.
     *
     * @param feedPost - пост
     * @param statisticPostItem - элемент статистики
     */
    fun updateCount(feedPost: FeedPost, statisticPostItem: StatisticPostItem) {
        val currentState = screenState.value
        if (currentState !is FeedPostScreenState.Posts) return

        // Получаем коллекцию старых элементов статистики и заменяем на новые значения в screenStat'е.
        val oldNews = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == statisticPostItem.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }

        // Копируем объект поста, но с новой статистикой и обновляем содержимое stat'а экрана постов.
        val newStatisticsCopy = feedPost.copy(statistics = newStatistics)
        val newPosts = oldNews.apply {
            replaceAll {
                if (it.id == newStatisticsCopy.id) {
                    newStatisticsCopy
                } else {
                    it
                }
            }
        }
        _screenState.value = FeedPostScreenState.Posts(posts = newPosts)
    }

    /**
     * Удаление поста.
     * TODO предусмотреть удаление только своих постов, чужие посты будут просто удалены из списка.
     *
     * @param feedPost - пост
     */
    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is FeedPostScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = FeedPostScreenState.Posts(posts = oldPosts)
    }
}