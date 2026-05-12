package com.karunada.kala.data

object MockData {
    val artForms = listOf(
        ArtForm(
            id = "yakshagana",
            name = "Yakshagana",
            category = "Performing Art",
            shortDesc = "Traditional theater form of Coastal Karnataka.",
            history = "Yakshagana is a rare combination of dance, music, song, scholarly dialogues and colourful costumes. A celestial world unfolds before the audience, as loud singing and drumming accompany a story being enacted.",
            origin = "Coastal Karnataka",
            imageUrl = "https://images.unsplash.com/photo-1626103659333-727a36230869?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "dollu_kunitha",
            name = "Dollu Kunitha",
            category = "Folk Dance",
            shortDesc = "Powerful drum dance performed by the Kuruba community.",
            history = "Dollu Kunitha is a major popular drum dance of Karnataka. Accompanied by singing, it provides spectacular variety and complexity of skills in the process of demonstration.",
            origin = "Central Karnataka",
            imageUrl = "https://images.unsplash.com/photo-1514336020557-e543103dd2a4?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "kinnala_toys",
            name = "Kinnala Toys",
            category = "Craft",
            shortDesc = "Exquisite handcrafted wooden toys from Kinhal.",
            history = "Kinhal is a village in Koppal district, famous for its lacquerware and wood carvings. The craft dates back to the Vijayanagara Empire.",
            origin = "Koppal",
            imageUrl = "https://images.unsplash.com/photo-1531259683007-016a7b628fc3?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "bidriware",
            name = "Bidriware",
            category = "Craft",
            shortDesc = "Metal handicraft from Bidur with silver inlay.",
            history = "Bidriware is a metal handicraft from Bidar. It is a GI tagged craft known for its striking silver inlay work against a dark background.",
            origin = "Bidar",
            imageUrl = "https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "ilkal_weaves",
            name = "Ilkal Weaves",
            category = "Craft",
            shortDesc = "Traditional handloom sarees from Ilkal.",
            history = "Ilkal sarees are a traditional form of sarees from the Bagalkot district. They are known for their unique 'Topi Teni' technique of joining the body and the pallu.",
            origin = "Bagalkot",
            imageUrl = "https://images.unsplash.com/photo-1589156191108-c762ff4b96ab?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "mysore_painting",
            name = "Mysore Painting",
            category = "Visual Art",
            shortDesc = "Classical South Indian painting from Mysore.",
            history = "Mysore painting is an important form of classical South Indian painting that evolved in the Mysore city of Karnataka. It is known for its elegance, muted colours, and attention to detail.",
            origin = "Mysuru",
            imageUrl = "https://images.unsplash.com/photo-1579783902614-a3fb3927b6a5?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "togalu_gombeyaata",
            name = "Togalu Gombeyaata",
            category = "Performing Art",
            shortDesc = "Shadow puppetry using leather puppets.",
            history = "Togalu Gombeyaata is a puppet show native to the state of Karnataka. Togalu Gombeyaata translates to a play of leather dolls in the native language of Kannada.",
            origin = "Central/North Karnataka",
            imageUrl = "https://images.unsplash.com/photo-1508739773434-c26b3d09e071?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "navalgund_durries",
            name = "Navalgund Durries",
            category = "Craft",
            shortDesc = "Exclusively designed carpets from Navalgund.",
            history = "Navalgund Durries are traditional woven floor mats featuring geometric and floral patterns, exclusively made in Navalgund town. They have a GI tag.",
            origin = "Dharwad",
            imageUrl = "https://images.unsplash.com/photo-1600166898405-da9535204843?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtForm(
            id = "bhoota_kola",
            name = "Bhoota Kola",
            category = "Performing Art",
            shortDesc = "Ritualistic spirit worship dance.",
            history = "Bhoota Kola is an ancient ritual performance from Tulu Nadu. It is an intricate form of worship involving music, dance, and spirit possession.",
            origin = "Dakshina Kannada",
            imageUrl = "https://images.unsplash.com/photo-1610123590390-d416083b04c7?q=80&w=600",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        )
    )

    val artisans = listOf(
        Artisan(
            id = "a1",
            name = "Guru Narayana Bhat",
            artForm = "Yakshagana",
            district = "Udupi",
            imageUrl = "https://images.unsplash.com/photo-1626103659333-727a36230869?q=80&w=300",
            type = HeritageType.PERFORMANCE
        ),
        Artisan(
            id = "a2",
            name = "Basavaraj Kinhal",
            artForm = "Kinnala Toys",
            district = "Koppal",
            imageUrl = "https://images.unsplash.com/photo-1531259683007-016a7b628fc3?q=80&w=300",
            type = HeritageType.WORKSHOP
        ),
        Artisan(
            id = "a3",
            name = "Mohammed Shakeel",
            artForm = "Bidriware",
            district = "Bidar",
            imageUrl = "https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?q=80&w=300",
            type = HeritageType.WORKSHOP
        ),
        Artisan(
            id = "a4",
            name = "Mallamma Devi",
            artForm = "Ilkal Weaves",
            district = "Bagalkot",
            imageUrl = "https://images.unsplash.com/photo-1589156191108-c762ff4b96ab?q=80&w=300",
            type = HeritageType.WORKSHOP
        ),
        Artisan(
            id = "a5",
            name = "Ranga Swamy",
            artForm = "Dollu Kunitha",
            district = "Tumkur",
            imageUrl = "https://images.unsplash.com/photo-1514336020557-e543103dd2a4?q=80&w=300",
            type = HeritageType.PERFORMANCE
        ),
        Artisan(
            id = "a6",
            name = "S. V. Rama Rao",
            artForm = "Mysore Painting",
            district = "Mysuru",
            imageUrl = "https://images.unsplash.com/photo-1579783902614-a3fb3927b6a5?q=80&w=300",
            type = HeritageType.WORKSHOP
        ),
        Artisan(
            id = "a7",
            name = "Puppeteer Hanumanthappa",
            artForm = "Togalu Gombeyaata",
            district = "Bellary",
            imageUrl = "https://images.unsplash.com/photo-1508739773434-c26b3d09e071?q=80&w=300",
            type = HeritageType.PERFORMANCE
        ),
        Artisan(
            id = "a8",
            name = "Begum Bi Navalgund",
            artForm = "Navalgund Durries",
            district = "Dharwad",
            imageUrl = "https://images.unsplash.com/photo-1600166898405-da9535204843?q=80&w=300",
            type = HeritageType.WORKSHOP
        )
    )

    val events = listOf(
        ArtEvent(
            id = "e1",
            title = "Grand Yakshagana Bayalata",
            description = "All-night open-air performance by the famous Saligrama Mela.",
            date = "Dec 12 - Dec 13",
            location = "Kundapura, Udupi",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtEvent(
            id = "e2",
            title = "State Folk Arts Festival",
            description = "Featuring Dollu Kunitha, Somana Kunitha and more.",
            date = "Jan 05 - Jan 10",
            location = "Janapada Loka, Ramanagara",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        ),
        ArtEvent(
            id = "e3",
            title = "Shadow Puppet Night",
            description = "Traditional Togalu Gombeyaata storytelling performance.",
            date = "Dec 28",
            location = "Bellary Ground",
            videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        )
    )

    val products = listOf(
        Product(
            id = "p1",
            name = "Hand-painted Kinhal Garuda",
            price = 3500.0,
            imageUrl = "https://images.unsplash.com/photo-1531259683007-016a7b628fc3?q=80&w=400",
            artisanId = "a2",
            description = "Traditional hand-carved wooden Garuda figure.",
            category = "Home Decor"
        ),
        Product(
            id = "p2",
            name = "Silver Inlay Bidri Vase",
            price = 4200.0,
            imageUrl = "https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?q=80&w=400",
            artisanId = "a3",
            description = "Zinc and Copper alloy vase with pure silver inlay.",
            category = "Metal Art"
        ),
        Product(
            id = "p3",
            name = "Authentic Ilkal Saree (Silk-Cotton)",
            price = 2800.0,
            imageUrl = "https://images.unsplash.com/photo-1589156191108-c762ff4b96ab?q=80&w=400",
            artisanId = "a4",
            description = "Classic red and maroon Ilkal saree with traditional pallu.",
            category = "Apparel"
        ),
        Product(
            id = "p4",
            name = "Mysore Gesso Painting - Ganesha",
            price = 5500.0,
            imageUrl = "https://images.unsplash.com/photo-1579783902614-a3fb3927b6a5?q=80&w=400",
            artisanId = "a6",
            description = "Gold leaf work painting on wood base.",
            category = "Fine Art"
        ),
        Product(
            id = "p5",
            name = "Navalgund Jamkhana (Durrie)",
            price = 6500.0,
            imageUrl = "https://images.unsplash.com/photo-1600166898405-da9535204843?q=80&w=400",
            artisanId = "a8",
            description = "Robust hand-woven cotton durrie with peacock motifs.",
            category = "Home Decor"
        )
    )

    val artFormNames = artForms.map { it.name }
}
