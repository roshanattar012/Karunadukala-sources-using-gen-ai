package com.karunada.kala.data

object MockData {
    val artForms = listOf(
        ArtForm(
            id = "yakshagana",
            name = "Yakshagana",
            category = "Performing Art",
            shortDesc = "Traditional theater form of Coastal Karnataka.",
            history = "Yakshagana is a rare combination of dance, music, song, scholarly dialogues and colourful costumes.",
            origin = "Coastal Karnataka",
            imageUrl = "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=600",
            videoUrl = "https://archive.org/download/yakshagana_performance/yakshagana.mp4"
        ),
        ArtForm(
            id = "dollu_kunitha",
            name = "Dollu Kunitha",
            category = "Folk Dance",
            shortDesc = "Powerful drum dance performed by the Kuruba community.",
            history = "Dollu Kunitha is a major popular drum dance of Karnataka.",
            origin = "Central Karnataka",
            imageUrl = "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=600",
            videoUrl = "https://archive.org/download/karnataka_folk_dance/dollu_kunitha.mp4"
        ),
        ArtForm(
            id = "kinnala_toys",
            name = "Kinnala Toys",
            category = "Craft",
            shortDesc = "Exquisite handcrafted wooden toys from Kinhal.",
            history = "Kinhal is a village in Koppal district, famous for its lacquerware and wood carvings.",
            origin = "Koppal",
            imageUrl = "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=600",
            videoUrl = "https://archive.org/download/handicraft_demos/kinnala_toys_making.mp4"
        ),
        ArtForm(
            id = "bidriware",
            name = "Bidriware",
            category = "Craft",
            shortDesc = "Metal handicraft from Bidur with silver inlay.",
            history = "Bidriware is a metal handicraft from Bidar. Striking silver inlay work against a dark background.",
            origin = "Bidar",
            imageUrl = "https://images.pexels.com/photos/11516067/pexels-photo-11516067.jpeg?auto=compress&cs=tinysrgb&w=600",
            videoUrl = "https://archive.org/download/handicraft_demos/bidriware_making.mp4"
        ),
        ArtForm(
            id = "ilkal_weaves",
            name = "Ilkal Weaves",
            category = "Craft",
            shortDesc = "Traditional handloom sarees from Ilkal.",
            history = "Ilkal sarees are a traditional form of sarees from the Bagalkot district.",
            origin = "Bagalkot",
            imageUrl = "https://images.pexels.com/photos/4505166/pexels-photo-4505166.jpeg?auto=compress&cs=tinysrgb&w=600",
            videoUrl = "https://archive.org/download/handicraft_demos/ilkal_weaving.mp4"
        ),
        ArtForm(
            id = "bhoota_kola",
            name = "Bhoota Kola",
            category = "Performing Art",
            shortDesc = "Ritualistic spirit worship dance.",
            history = "Ancient ritual performance from Tulu Nadu involving music, dance, and spirit possession.",
            origin = "Dakshina Kannada",
            imageUrl = "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=600",
            videoUrl = "https://archive.org/download/cultural_rituals_karnataka/bhoota_kola.mp4"
        ),
        ArtForm(
            id = "lambani_embroidery",
            name = "Lambani Embroidery",
            category = "Craft",
            shortDesc = "Intricate mirror and thread work.",
            history = "Unique combination of colorful threads and mirror work traditional to the nomadic Lambani tribes.",
            origin = "North Karnataka",
            imageUrl = "https://images.pexels.com/photos/16124451/pexels-photo-16124451.jpeg?auto=compress&cs=tinysrgb&w=600",
            videoUrl = "https://archive.org/download/handicraft_demos/lambani_embroidery.mp4"
        )
    )

    val artisans = listOf(
        // PERFORMANCES
        Artisan("p1", "Guru Narayana Bhat", "Yakshagana", "Udupi", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p2", "Ranga Swamy", "Dollu Kunitha", "Tumkur", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p3", "Manjunath Gowda", "Yakshagana", "Sirsi", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p4", "Lokesh Kumar", "Dollu Kunitha", "Shimoga", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p5", "Shiva Tulu", "Bhoota Kola", "Mangaluru", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p6", "Keshava Hegde", "Yakshagana", "Honnavar", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p7", "Siddaiah", "Dollu Kunitha", "Mandya", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p8", "Babu Moolya", "Kambala", "Udupi", "https://images.pexels.com/photos/2253818/pexels-photo-2253818.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p9", "Ramappa", "Gombe-ata", "Channapatna", "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p10", "Dinesh", "Veeragase", "Chikmagalur", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p11", "Yogesh", "Yakshagana", "Karkala", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p12", "Chinnayya", "Gorava Kunitha", "Haveri", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p13", "Mariyappa", "Somana Kunitha", "Tumkur", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p14", "Basappa", "Suggi Kunitha", "Karwar", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p15", "Venkatesh", "Yakshagana", "Kundapur", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p16", "Lingaraju", "Dollu Kunitha", "Hassan", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p17", "Subba Rao", "Yakshagana", "Kumta", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p18", "Ananda", "Bhoota Kola", "Udupi", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p19", "Eshwarappa", "Karadi Majalu", "Dharwad", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p20", "Puttaiah", "Kamsale", "Mysuru", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p21", "Nagendra", "Yakshagana", "Bantwal", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p22", "Mallappa", "Dollu Kunitha", "Gadag", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p23", "Ganesh", "Veeragase", "Kodagu", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p24", "Suresh", "Yakshagana", "Puttur", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p25", "Thippesha", "Dollu Kunitha", "Chitradurga", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p26", "Krishna", "Bhoota Kola", "Sullia", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p27", "Satish", "Yakshagana", "Byndoor", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p28", "Hanumantha", "Dollu Kunitha", "Bagalkot", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p29", "Ravi", "Veeragase", "Chamarajanagar", "https://images.pexels.com/photos/12316431/pexels-photo-12316431.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),
        Artisan("p30", "Shantappa", "Yakshagana", "Yellapur", "https://images.pexels.com/photos/10103758/pexels-photo-10103758.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.PERFORMANCE),

        // WORKSHOPS
        Artisan("w1", "Basavaraj Kinhal", "Kinnala Toys", "Koppal", "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w2", "Mohammed Shakeel", "Bidriware", "Bidar", "https://images.pexels.com/photos/11516067/pexels-photo-11516067.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w3", "Mallamma Devi", "Ilkal Weaves", "Bagalkot", "https://images.pexels.com/photos/4505166/pexels-photo-4505166.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w4", "Seethamma Lambani", "Lambani Embroidery", "Sandur", "https://images.pexels.com/photos/16124451/pexels-photo-16124451.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w5", "Chennappa", "Channapatna Toys", "Ramnagaram", "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w6", "Savitramma", "Mysore Painting", "Mysuru", "https://images.pexels.com/photos/1183992/pexels-photo-1183992.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w7", "Ibrahim", "Bidriware", "Bidar", "https://images.pexels.com/photos/11516067/pexels-photo-11516067.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w8", "Radha Devi", "Kasuti Embroidery", "Dharwad", "https://images.pexels.com/photos/16124451/pexels-photo-16124451.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w9", "Guruprasad", "Sandalwood Carving", "Shivamogga", "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w10", "Lakshmamma", "Navalgund Durries", "Navalgund", "https://images.pexels.com/photos/4505166/pexels-photo-4505166.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w11", "Abdul Karim", "Bidriware", "Bidar", "https://images.pexels.com/photos/11516067/pexels-photo-11516067.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w12", "Parvati", "Lambani Embroidery", "Hampi", "https://images.pexels.com/photos/16124451/pexels-photo-16124451.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w13", "Sidramappa", "Kinnala Toys", "Koppal", "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w14", "Gowramma", "Ilkal Weaves", "Ilkal", "https://images.pexels.com/photos/4505166/pexels-photo-4505166.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w15", "Govindappa", "Leather Puppetry", "Dharmavaram", "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w16", "Revathi", "Mysore Painting", "Mysuru", "https://images.pexels.com/photos/1183992/pexels-photo-1183992.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w17", "Mustafa", "Bidriware", "Bidar", "https://images.pexels.com/photos/11516067/pexels-photo-11516067.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w18", "Shankara", "Wood Carving", "Sagar", "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w19", "Lalithamma", "Navalgund Durries", "Dharwad", "https://images.pexels.com/photos/4505166/pexels-photo-4505166.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w20", "Bhagyamma", "Lambani Embroidery", "Bellary", "https://images.pexels.com/photos/16124451/pexels-photo-16124451.jpeg?auto=compress&cs=tinysrgb&w=300", HeritageType.WORKSHOP),
        Artisan("w21", "Shivakumar", "Kinnala Toys", "Koppal", "https://images.unsplash.com/photo-1531259683007-016a7b628fc3?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w22", "Sharada", "Ilkal Weaves", "Bagalkot", "https://images.unsplash.com/photo-1589156191108-c762ff4b96ab?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w23", "Ismail", "Bidriware", "Bidar", "https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w24", "Kamala", "Kasuti Embroidery", "Hubli", "https://images.unsplash.com/photo-1610116303244-6239f893fb7f?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w25", "Narasimha", "Stone Carving", "Shivarapatna", "https://images.unsplash.com/photo-1582555172866-f73bb12a2ab3?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w26", "Rajeswari", "Mysore Silk Weaving", "Mysuru", "https://images.unsplash.com/photo-1589156191108-c762ff4b96ab?q=80&w=400", HeritageType.WORKSHOP),
        Artisan("w27", "Yusuf", "Bidriware", "Bidar", "https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w28", "Devamma", "Navalgund Durries", "Navalgund", "https://images.unsplash.com/photo-1590070275202-0c9f1315b4d4?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w29", "Ramalingappa", "Kinnala Toys", "Koppal", "https://images.unsplash.com/photo-1531259683007-016a7b628fc3?q=80&w=300", HeritageType.WORKSHOP),
        Artisan("w30", "Sumitra", "Lambani Embroidery", "Sandur", "https://images.unsplash.com/photo-1610116303244-6239f893fb7f?q=80&w=300", HeritageType.WORKSHOP)
    )

    val events = listOf(
        ArtEvent("e1", "Grand Yakshagana Bayalata", "All-night open-air performance by the famous Saligrama Mela showcasing the battle of Abhimanyu.", "Dec 12 - Dec 13", "Saligrama, Udupi", "https://archive.org/download/yakshagana_performance/yakshagana.mp4"),
        ArtEvent("e2", "State Folk Arts Festival", "Featuring Dollu Kunitha, Somana Kunitha and more than 50 other folk forms from across Karnataka.", "Jan 05 - Jan 10", "Janapada Loka, Ramanagara", "https://archive.org/download/karnataka_folk_dance/dollu_kunitha.mp4"),
        ArtEvent("e3", "Bhoota Kola Ritual", "Witness the divine spirit worship ritual of Tulu Nadu in its most authentic form.", "Dec 28 - Dec 29", "Mangaluru, Dakshina Kannada", "https://archive.org/download/cultural_rituals_karnataka/bhoota_kola.mp4"),
        ArtEvent("e4", "Bidriware Craft Expo", "Exhibition and live demonstration of the 14th-century metal handicraft from Bidar.", "Feb 14 - Feb 16", "Chitrakala Parishath, Bengaluru", "https://archive.org/download/handicraft_demos/bidriware_making.mp4"),
        ArtEvent("e5", "Dollu Kunitha Championship", "The biggest rhythmic drum performance competition in Karnataka.", "Jan 20", "Chitradurga Fort", "https://archive.org/download/karnataka_folk_dance/dollu_kunitha_short.mp4"),
        ArtEvent("e6", "Kambala Buffalo Race", "The legendary traditional slush-track race of Coastal Karnataka.", "Dec 15", "Puttur", "https://archive.org/download/karnataka_sports_heritage/kambala_race.mp4")
    )

    val products = listOf(
        Product("p1", "Hand-painted Kinhal Garuda", 3500.0, "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=400", "w1", "Traditional hand-carved wooden Garuda figure.", "Home Decor"),
        Product("p2", "Silver Inlay Bidri Vase", 4200.0, "https://images.pexels.com/photos/11516067/pexels-photo-11516067.jpeg?auto=compress&cs=tinysrgb&w=400", "w2", "Zinc and Copper alloy vase with pure silver inlay.", "Metal Art"),
        Product("p3", "Authentic Ilkal Saree", 2800.0, "https://images.pexels.com/photos/4505166/pexels-photo-4505166.jpeg?auto=compress&cs=tinysrgb&w=400", "w3", "Classic Ilkal saree with traditional pallu.", "Apparel"),
        Product("p4", "Channapatna Wooden Train", 850.0, "https://images.pexels.com/photos/3663060/pexels-photo-3663060.jpeg?auto=compress&cs=tinysrgb&w=400", "w5", "Eco-friendly lacquered wooden toys.", "Toys"),
        Product("p5", "Lambani Mirror-work Bag", 1200.0, "https://images.pexels.com/photos/16124451/pexels-photo-16124451.jpeg?auto=compress&cs=tinysrgb&w=400", "w4", "Hand-embroidered bag with mirror work.", "Fashion")
    )

    val artFormNames = artForms.map { it.name }
}
