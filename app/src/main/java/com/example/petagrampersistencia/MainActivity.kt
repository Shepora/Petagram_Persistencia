package com.example.petagrampersistencia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, posicion ->
            tab.setIcon(if (posicion == 0) R.drawable.ic_home else R.drawable.ic_paw)
        }.attach()

        findViewById<FloatingActionButton>(R.id.fabCamara).setOnClickListener { v ->
            Snackbar.make(v, "Subir foto (próximamente)", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu.findItem(R.id.action_favoritas).actionView?.setOnClickListener {
            startActivity(Intent(this, MascotasFavoritasActivity::class.java))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_contacto -> {
                startActivity(Intent(this, ContactoActivity::class.java))
                true
            }
            R.id.action_acerca -> {
                startActivity(Intent(this, AcercaDeActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
