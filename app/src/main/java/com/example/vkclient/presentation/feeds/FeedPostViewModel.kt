package com.example.vkclient.presentation.feeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vkclient.data.repository.FeedPostRepository
import com.example.vkclient.domain.FeedPost
import com.example.vkclient.domain.StatisticPostItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * View model для постов.
 *
 * @constructor Create empty Feed post view model
 */
class FeedPostViewModel(application: Application) : AndroidViewModel(application) {

    // Начаьный state для постов.
    private val initialState = FeedPostScreenState.Initial

    private val feedPostRepository = FeedPostRepository(application)

    // State для поста
    private val _screenState = MutableLiveData<FeedPostScreenState>(initialState)
    val screenState: LiveData<FeedPostScreenState> = _screenState

    init {
        _screenState.value = FeedPostScreenState.Loading
        loadFeedPosts()
    }

    private fun loadFeedPosts() {
        viewModelScope.launch {
            val feedPosts = feedPostRepository.loadFeedPosts()
            _screenState.value = FeedPostScreenState.Posts(posts = feedPosts)
        }
    }

    /**
     * Загрузка следующих постов (подгрузка).
     *
     */
    fun loadNextFeedPosts() {
        _screenState.value = FeedPostScreenState.Posts(
            posts = feedPostRepository.feedPosts,
            nextFeedPostsIsLoading = true
        )
        loadFeedPosts()
    }

    /**
     * Изменение статуса лафка.
     *
     * @param feedPost - новостной пост
     */
    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            feedPostRepository.changeLikeStatus(feedPost)
            _screenState.value = FeedPostScreenState.Posts(posts = feedPostRepository.feedPosts)
        }
    }

//    /**
//     * Изменение значений для элемента статистика поста.
//     *
//     * @param feedPost - пост
//     * @param statisticPostItem - элемент статистики
//     */
//    fun updateCount(feedPost: FeedPost, statisticPostItem: StatisticPostItem) {
//        val currentState = screenState.value
//        if (currentState !is FeedPostScreenState.Posts) return
//
//        // Получаем коллекцию старых элементов статистики и заменяем на новые значения в screenStat'е.
//        val oldNews = currentState.posts.toMutableList()
//        val oldStatistics = feedPost.statistics
//        val newStatistics = oldStatistics.toMutableList().apply {
//            replaceAll { oldItem ->
//                if (oldItem.type == statisticPostItem.type) {
//                    oldItem.copy(count = oldItem.count + 1)
//                } else {
//                    oldItem
//                }
//            }
//        }
//
//        // Копируем объект поста, но с новой статистикой и обновляем содержимое stat'а экрана постов.
//        val newStatisticsCopy = feedPost.copy(statistics = newStatistics)
//        val newPosts = oldNews.apply {
//            replaceAll {
//                if (it.id == newStatisticsCopy.id) {
//                    newStatisticsCopy
//                } else {
//                    it
//                }
//            }
//        }
//        _screenState.value = FeedPostScreenState.Posts(posts = newPosts)
//    }

    /**
     * Удаление поста.
     * TODO предусмотреть удаление только своих постов, чужие посты будут просто удалены из списка.
     *
     * @param feedPost - пост
     */
    fun remove(feedPost: FeedPost) {
        viewModelScope.launch {
            feedPostRepository.ignoreFeedPost(feedPost)
            _screenState.value = FeedPostScreenState.Posts(posts = feedPostRepository.feedPosts)
        }
    }
}