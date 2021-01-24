# chatbot-web-service
Chatbot için bütün verilerin modeller üzerinden dönmesini sağlayarak, katmanlı mimarı yapısı geliştirilmiştir. 
Ve Veritabanına yük bindirmemek adına ön işleme kısmıda eklenmiştir.
Ön işleme = Yazılan mesajda gereksiz olan kısmı yani anlamsız olabilecek kısmı, örneğin "bugün hava ne kadar güzel" kelimesindeki "ne" kelimesinin bir anlama gelmediği
için hem arka tarafta sunucuya gereksiz yük bindirmemek hemde makine öğrenmesi algoritmalarına sokmadan önce gereksiz olan kelimelerin temizlenmesi.
Bu sayede algolaritmaların doğruluk derecesi hem artmış bir biçimde karşımıza çıkacak hemde backend kısmına daha yalın veri götürdüğümüz için daha optimize 
bir yapı sunmuştur.
