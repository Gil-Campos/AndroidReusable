package com.example.retrofibasicstultimate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofibasicstultimate.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.e("RETROFIT_CALL", "onCreate: IOException, ${e.message}")
                binding.progressBar.isVisible = false
                return@launch
            } catch (e: HttpException) {
                Log.e("RETROFIT_CALL", "onCreate: HttpException, ${e.message}")
                binding.progressBar.isVisible = false
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                todoAdapter.todos = response.body()!!
            } else {
                Log.e("RETROFIT_CALL", "Response not successful")
            }

            binding.progressBar.isVisible = false
        }

        setUpRecyclerview()
    }

    private fun setUpRecyclerview() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}