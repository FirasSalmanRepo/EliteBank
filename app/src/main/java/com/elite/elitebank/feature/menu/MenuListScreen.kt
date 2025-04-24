package com.elite.elitebank.feature.menu

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elite.elitebank.R
import com.elite.elitebank.core.UIText
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.Paddings
import com.elite.elitebank.ui.theme.component.cards.EliteWhiteCard
import com.elite.elitebank.ui.theme.component.dialogs.EliteAlertDialog
import com.elite.elitebank.ui.theme.component.dividers.EliteDivider
import com.elite.elitebank.ui.theme.component.images.EliteIllustration
import com.elite.elitebank.ui.theme.headline2
import com.elite.elitebank.ui.theme.regular
import com.elite.elitebank.R.drawable as UIDrawables

@Composable
fun MenuListScreen(
    state: MenuListScreenState,
    onEvent: (MenuListEvent) -> Unit
) = MenuListScreenContent(state = state, onEvent = onEvent)

@Composable
private fun MenuListScreenContent(
    state: MenuListScreenState,
    onEvent: (MenuListEvent) -> Unit
) {

    if (state.isConfirmLogoutPopupVisible) {
        EliteAlertDialog(
            title = "Log out?",
            optionalSubtitle = "Logging out from the app will clear all your saved locations and data.",
            primaryButtonTextToActionPair = "Confirm" to {
                onEvent(MenuListEvent.OnConfirmLogoutPopupAction(true))
            },
            optionalSecondaryButtonTextToActionPair = "Cancel" to {
                onEvent(MenuListEvent.OnConfirmLogoutPopupAction(false))
            }
        ) { }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(EliteColors.screenBackground.secondary),
        contentAlignment = Alignment.Center
    ) {

        EliteIllustration(
            illustration = R.drawable.ill_more_bg,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            optionalContentScale = ContentScale.FillWidth
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            contentPadding = PaddingValues(
                top = LocalConfiguration.current.screenHeightDp.dp * .13f,
                end = Paddings.large,
                bottom = Paddings.large,
                start = Paddings.large
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(EliteColors.accent.light),
                    contentAlignment = Alignment.Center
                ) {
                    EliteIllustration(
                        illustration = R.drawable.ic_salman_image,
                        modifier = Modifier.width(150.dp) .height(150.dp),
                        optionalContentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Text(
                    text = state.userName,
                    color = EliteColors.text.dark,
                    style = headline2()
                )

                Spacer(modifier = Modifier.height(Paddings.small))

                Text(
                    text = state.emailId,
                    color = EliteColors.text.light,
                    style = regular()
                )

                Spacer(modifier = Modifier.height(Paddings.default))

                EliteDivider(Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(Paddings.default))
            }

            items(
                listOf(
                    Triple(
                        UIDrawables.ic_profile,
                        UIText.DynamicString("Profile"),
                        MoreListItem.PROFILE
                    ),
                    Triple(
                        UIDrawables.ic_location,
                        UIText.DynamicString("Saved Locations"),
                        MoreListItem.SAVED_LOCATIONS
                    ),
                    Triple(UIDrawables.ic_faq, UIText.DynamicString("FAQ"), MoreListItem.FAQ)
                )
            ) {
                MoreItemRow(
                    icon = it.first,
                    name = it.second
                ) {
                    onEvent(MenuListEvent.OnItemClicked(it.third))
                }

                Spacer(modifier = Modifier.height(Paddings.small))
            }

            item {
                Spacer(modifier = Modifier.height(Paddings.default))

                EliteDivider(Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(Paddings.default))
            }

            items(
                listOf(
                    Triple(
                        UIDrawables.ic_settings,
                        UIText.DynamicString("Settings"),
                        MoreListItem.SETTINGS
                    ),
                    Triple(
                        UIDrawables.ic_about_us,
                        UIText.DynamicString("About Us"),
                        MoreListItem.ABOUT_US
                    ),
                    Triple(
                        UIDrawables.ic_contact_us,
                        UIText.DynamicString("Contact Us"),
                        MoreListItem.CONTACT_US
                    ),
                    Triple(
                        UIDrawables.ic_logout,
                        UIText.DynamicString("Logout"),
                        MoreListItem.LOGOUT
                    )
                )
            ) {

                MoreItemRow(
                    icon = it.first,
                    name = it.second
                ) {
                    onEvent(MenuListEvent.OnItemClicked(it.third))
                }

                Spacer(modifier = Modifier.height(Paddings.small))
            }

            item {
                Spacer(modifier = Modifier.height(Paddings.default))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = state.appName,
                        style = headline2(),
                        color = EliteColors.text.dark
                    )

                    Spacer(modifier = Modifier.height(Paddings.small))

                    Text(
                        text = "App Version ${state.appVersion}",
                        style = regular(),
                        color = EliteColors.text.light
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Paddings.default))
    }
}

@Composable
private fun MoreItemRow(
    @DrawableRes icon: Int,
    name: UIText,
    onClickAction: () -> Unit
) = EliteWhiteCard(
    modifier = Modifier.fillMaxWidth(),
    onClickAction = onClickAction
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Paddings.default,
                vertical = Paddings.default
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = EliteColors.primary.dark
        )

        Spacer(modifier = Modifier. width(Paddings.default))

        Text(
            text = name.asString(LocalContext.current),
            style = regular().copy(
                lineHeight = 24.sp
            ),
            color = EliteColors.text.dark
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = UIDrawables.ic_chevron_right),
            contentDescription = null,
            tint = EliteColors.primary.dark
        )
    }
}

@Preview
@Composable
private fun MenuListScreenPreview() = MenuListScreen(
    state = MenuListScreenState(
        userName = "Firas Salman",
        emailId = "FirasSalman.93@gmail.com",
        appName = "Elite Bank App",
        appVersion = "1.0"
    )
) { }

@Preview
@Composable
private fun MenuListScreenLogoutPreview() = MenuListScreen(
    state = MenuListScreenState(
        userName = "Firas Salman",
        emailId = "FirasSalman.93@gmail.com",
        appName = "Elite Bank App",
        appVersion = "1.0",
        isConfirmLogoutPopupVisible = true
    )
) { }
