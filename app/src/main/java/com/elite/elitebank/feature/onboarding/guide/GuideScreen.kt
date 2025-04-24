package com.elite.elitebank.feature.onboarding.guide

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.elite.elitebank.R
import com.elite.elitebank.data.model.GuideModel
import com.elite.elitebank.ui.theme.EliteColors
import com.elite.elitebank.ui.theme.Paddings
import com.elite.elitebank.ui.theme.component.buttons.ElitePrimaryButton
import com.elite.elitebank.ui.theme.component.buttons.EliteTextButton
import com.elite.elitebank.ui.theme.component.images.EliteIllustration
import com.elite.elitebank.ui.theme.component.scaffold.EliteScaffold
import com.elite.elitebank.ui.theme.headline1
import com.elite.elitebank.ui.theme.regular
import com.elite.elitebank.ui.theme.utils.getImageBitmapFromVector

@Composable
fun GuideScreen(
    onEvent: (GuideEvent) -> Unit
) = GuideScreenContent(onEvent = onEvent)

@Composable
private fun GuideScreenContent(
    initialPage: Int = 0,
    onEvent: (GuideEvent) -> Unit
) = EliteScaffold(modifier = Modifier.fillMaxSize()) {

    val context = LocalContext.current
    val localDensity = LocalDensity.current

    val guideList = ArrayList<GuideModel>()
    guideList.add(
        GuideModel(
            R.drawable.ic_intro_one,
            "Elite Bank DashBoard",
            "Fully comprehensive digital dashboard to show your real-time financial data on hand with interactive design"
        )
    )
    guideList.add(
        GuideModel(
            R.drawable.ic_intro_2,
            "Card Operation",
            "Involves managing card issuance, activation, PIN setup, limits, and security controls for smooth and safe usage."
        )
    )
    guideList.add(
        GuideModel(
            R.drawable.ic_intro_3,
            "Card Transaction",
            "Enables customers to make secure payments or withdrawals using debit or credit cards via ATMs, POS terminals, or online platforms."
        )
    )
    val pagerState = rememberPagerState(initialPage) { guideList.size }
    val currentPage = pagerState.currentPage

    val illustrationImages = remember(guideList) {
        listOfNotNull(
            context.getImageBitmapFromVector(guideList.first().image),
            context.getImageBitmapFromVector(guideList[1].image),
            context.getImageBitmapFromVector(guideList.last().image)
        )
    }

    var parallaxOffset by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPageOffsetFraction }.collect {
            val fraction = pagerState.getOffsetDistanceInPages(0)
            parallaxOffset = fraction * (illustrationImages.first().width / guideList.size)
        }
    }

    var illustrationHeight by remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Paddings.xLarge)
                    .background(Color.Transparent)
            ) {

                Spacer(modifier = Modifier.height(illustrationHeight))

                Spacer(modifier = Modifier.height(80.dp))

                Spacer(modifier = Modifier.height(Paddings.xLarge))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    EliteIllustration(
                        illustration = guideList[currentPage].image,
                        modifier = Modifier.width(3000.dp) .height(300.dp),
                        optionalContentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = guideList[currentPage].title,
                        style = headline1(),
                        color = EliteColors.text.dark
                    )

                    Spacer(modifier = Modifier.height(Paddings.default))

                    Text(
                        text = guideList[currentPage].description,
                        style = regular(),
                        color = EliteColors.text.light,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(Paddings.default))

                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = Paddings.large),

            horizontalArrangement = Arrangement.spacedBy(Paddings.small)
        ) {
            repeat(guideList.size) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(
                            if (it == pagerState.currentPage) {
                                EliteColors.primary.default
                            } else {
                                EliteColors.text.light
                            }
                        )
                )
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(Paddings.xLarge)
        ) {

            Spacer(modifier = Modifier.weight(1f))

            ElitePrimaryButton(text = "Get Started", Modifier.fillMaxWidth()) {
                Log.d("GuideScreen", "Get Started Clicked")
                onEvent(GuideEvent.OnGetStartedClicked)

                
            }

            Spacer(modifier = Modifier.height(Paddings.default))
        }

        EliteTextButton(
            text = "Skip",
            modifier = Modifier
                .padding(end = Paddings.xSmall)
                .align(Alignment.TopEnd)
        ) {
            onEvent(GuideEvent.OnSkipClicked)
        }
    }
}

@PreviewLightDark
@Composable
private fun GuideScreenPreview(
    @PreviewParameter(InitialPagePPP::class)
    initialPage: Int
) = GuideScreenContent(initialPage) {}

private class InitialPagePPP(
    override val values: Sequence<Int> = sequenceOf(0, 1, 2)
) : PreviewParameterProvider<Int>

