package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import popup.TextFields;

//TODO make the program search depending on what the input starts with

public class SearchBarSection {

    public TextField search;

    public SearchBarSection(Pane pane) {
        search = new TextField();
        search.setPromptText("search for others!");
        search.setLayoutX(10);
        search.setLayoutY(26);
        pane.getChildren().add(search);

        //temp
        String[] a = {"EleanorBowman2050",
        "EmilyHunt1509",
                "LincolnSwanson2879",
                "JacobMalone706",
                "Harpernull1759",
                "Noranull4111",
                "LillianMorrison3176",
                "MilaHartman4494",
                "WyattMalone2695",
                "JoshuaLeigh743",
                "JohnLove421",
                "LoganAyala4655",
                "AbigailPeters2203",
                "JohnMcgrath2857",
                "Chloenull4392",
                "AmeliaCartwright3298",
                "ElijahPerkins3519",
                "JamesDawson457",
                "MadisonMalone1498",
                "AuroraHammond3674",
                "IsabellaClarke317",
                "NoahHunt1674",
                "GraceHunt4875",
                "JacksonWood3142",
                "ClaireWilliamson2684",
                "NoraMorrison2943",
                "JackHolmes1311",
                "HazelBryant1880",
                "ScarlettBass4016",
                "ElizabethBlaese3312",
                "GraceHartman2694",
                "AvaSchneider2819",
                "NoraLloyd242",
                "VioletGeorge1618",
                "EthanDoyle485",
                "WilliamBass1673",
                "DavidFletcher4559",
                "LeviCarroll3507",
                "MichaelHammond4209",
                "JamesButler4920",
                "BenjaminMcgrath2622",
                "Sofianull1352",
                "StellaCarter4459",
                "JamesDoyle3688",
                "ZoeyHartman3883",
                "AsherClarke4150",
                "CarterWheeler293",
                "CharlotteHammond2122",
                "SofiaWilliams4656",
                "PenelopeCraig929",
                "ChristopherReyes3058",
                "SkylarCraig3127",
                "GraceDouglas2555",
                "AnthonyHorne2300",
                "SkylarLloyd4229",
                "Lukenull4332",
                "SavannahDavis4546",
                "EmmaSawyer4735",
                "Elijahnull3385",
                "HannahFletcher4166",
                "VictoriaKnowles2818",
                "MasonHamilton958",
                "BrooklynRodriguez2094",
                "HenryHorne1647",
                "MatthewDavis2447",
                "HazelRodriguez4667",
                "JacksonHardy1356",
                "SamuelClarke76",
                "ScarlettBass2597",
                "JohnLove3522",
                "EllaLeigh887",
                "HenryBass696",
                "SavannahHartman4189",
                "LiamFlores4085",
                "ClaireRodriguez3195",
                "StellaCartwright4690",
                "GraceCartwright2275",
                "HenryByrne260",
                "GraceHorton3035",
                "EvelynMorrison4808",
                "SophiaKnowles2268",
                "AriaGreene324",
                "OwenHartman3842",
                "IsabellaWood206",
                "StellaHampton3876",
                "Ryannull3256",
                "AnthonyHamilton1034",
                "Amelianull2942",
                "Clairenull1501",
                "LincolnHarper2712",
                "ElizabethDouglas1283",
                "LillianHammond396",
                "Natalienull4251",
                "MasonPark509",
                "PenelopeWheeler1420",
                "LiamMitchell4077",
                "JoshuaCarter1740",
                "AndrewPerkins525",
                "NoraBass478",
                "JaxonReyes589"};

        TextFields.bindAutoCompletion(search,a);

        EventHandler searchBarEvent = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println(search.getText());
            }
        };

        search.addEventFilter(KeyEvent.KEY_RELEASED, searchBarEvent);
    }
}
