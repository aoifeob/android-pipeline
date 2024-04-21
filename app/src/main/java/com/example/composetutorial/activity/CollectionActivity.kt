package com.example.composetutorial.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.R
import com.example.composetutorial.model.card.Card
import com.example.composetutorial.model.card.CardColour
import com.example.composetutorial.ui.theme.CardboardCompanionTheme
import kotlinx.coroutines.launch

class CollectionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardboardCompanionTheme {
                NavMenu()
            }
        }
    }

    @Composable
    fun CollectionView(modifier: Modifier = Modifier) {
        // TODO: determine if library is empty based on stored card count
        //val isLibraryEmpty by remember { mutableStateOf(true) }

        val isLibraryEmpty by rememberSaveable { mutableStateOf(false) }
        val searchParam by rememberSaveable { mutableStateOf("") }
        val sortParam by rememberSaveable { mutableStateOf("") }
        val filterParams by rememberSaveable { mutableStateOf("") }

        Column {
            Surface(modifier = modifier.weight(1f), color = MaterialTheme.colorScheme.background) {
                if (isLibraryEmpty) {
                    OnboardingScreen()
                } else {
                    CardCollection(cards = getTestCardCollection())
                }
            }
            CustomiseResultsMenu()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NavMenu() {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        fun closeDrawer() = scope.launch { drawerState.close() }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text(
                        "Menu",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Divider()
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Scan New Card",
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                                contentDescription = null
                            )
                        },
                        selected = false,
                        onClick = {
                            /*TODO: Navigate to Screen*/
                            closeDrawer()
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "View Collection",
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_folder_24),
                                contentDescription = null
                            )
                        },
                        selected = false,
                        onClick = {
                            /*TODO: Navigate to Screen*/
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Add New Folder",
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_create_new_folder_24),
                                contentDescription = null
                            )
                        },
                        selected = false,
                        onClick = {
                            /*TODO: Navigate to Screen*/
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "How to Use",
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_question_mark_24),
                                contentDescription = null
                            )
                        },
                        selected = false,
                        onClick = {
                            /*TODO: Navigate to Screen*/
                            closeDrawer()
                        })
                }
            },
            gesturesEnabled = true
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                "My Collection",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }, content = {
                                Icon(
                                    imageVector = Icons.Default.Menu, contentDescription = null
                                )
                            })
                        },
                        actions = {
                            SettingsMenu()
                        }
                    )
                }
            ) {
                CollectionView(modifier = Modifier.padding(it))
            }
        }
    }

    @Composable
    fun SettingsMenu() {
        var expanded by rememberSaveable { mutableStateOf(false) }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
        }

        /*TODO: display settings menu when expanded:
            - Rename folder
            - Move folder (disabled for parent folder)
            - Delete folder (disabled for parent folder)
         */
    }

    @Composable
    fun CustomiseResultsMenu() {
        Surface(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchMenu()
                Spacer(modifier = Modifier.weight(1f))
                FilterMenu()
                Spacer(modifier = Modifier.weight(1f))
                SortMenu()
            }
        }
    }

    @Composable
    fun SearchMenu() {
        var expanded by rememberSaveable { mutableStateOf(false) }
        Button(
            onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }

        /*TODO: display search menu when expanded:
            - Search by card name
            - Search by set name
         */
    }

    @Composable
    fun FilterMenu() {
        var expanded by rememberSaveable { mutableStateOf(false) }
        Button(
            onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_alt_24),
                contentDescription = null
            )
        }
        /*TODO: display filter menu when expanded:
            - Price
            - Set
            - Colour(s)
        */
    }

    @Composable
    fun SortMenu() {
        var expanded by rememberSaveable { mutableStateOf(false) }
        Button(
            onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_sort_24),
                contentDescription = null
            )
        }

        /*TODO: display sort menu when expanded:
            - Name
            - Price
            - Set
            - Colour(s)
        */
    }

    @Composable
    fun CardCollection(cards: List<Card>) {
        LazyColumn(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {
            items(cards) { card ->
                Card(card)
            }
        }
    }

    @Composable
    fun Card(card: Card) {
        Column(
            modifier = Modifier.padding(all = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                CardPreview(card)
            }
            Spacer(modifier = Modifier.height(4.dp))
            CardDetails(card)
        }
    }

    @Composable
    fun CardPreview(card: Card) {
        Surface {
            Image(
                painter = painterResource(id = card.image),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
            )
            if (card.quantity > 1) {
                Surface(
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .size(20.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, Color.White, CircleShape)
                ) {
                    Text(
                        text = card.quantity.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }

    @Composable
    fun CardDetails(card: Card) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = card.getDisplayName(),
                modifier = Modifier.padding(all = 4.dp),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = MaterialTheme.colorScheme.background
            ) {
                Text(
                    text = card.getDisplayPrice(),
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }

    @Composable
    fun OnboardingScreen(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome to Cardboard Companion!",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.padding(15.dp))
            Text(
                "Add some cards to your collection to get started",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = { /* TODO: redirect to scanner */ }
            ) {
                Text("Scan a Card")
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun CollectionViewPreview() {
        CardboardCompanionTheme {
            NavMenu()
        }
    }

    private fun getTestCardCollection(): List<Card> {
        return listOf(
            Card(
                1,
                "Lightning Bolt",
                "2X2",
                117,
                R.drawable.card_lightning_bolt_2x2_117,
                2.30,
                4,
                listOf(CardColour.RED)
            ),
            Card(
                1,
                "Lightning Bolt",
                "CLB",
                187,
                R.drawable.card_lightning_bolt_clb_187,
                1.18,
                2,
                listOf(CardColour.RED)
            ),
            Card(
                1,
                "Humility",
                "TPR",
                16,
                R.drawable.card_humility_tpr_16,
                36.76,
                1,
                listOf(CardColour.WHITE)
            ),
            Card(
                1,
                "Horizon Canopy",
                "IMA",
                240,
                R.drawable.card_horizon_canopy_ima_240,
                5.25,
                4,
                listOf(CardColour.GREEN, CardColour.WHITE)
            ),
            Card(
                1,
                "Thalia's Lancers",
                "EMN",
                47,
                R.drawable.card_thalia_s_lancers_emn_47,
                0.45,
                3,
                listOf(CardColour.WHITE)
            )
        )
    }

}