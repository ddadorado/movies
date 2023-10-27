//
//  AllMoviesView.swift
//  moviesMp
//
//  Created by Training Sj on 10/26/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import AsyncImage

struct AllMoviesView: View {
    @ObservedObject var viewModel: MoviesViewModel
    //@Environment(\.managedObjectContext) var moc
    @Environment(\.managedObjectContext) private var viewContext

    @FetchRequest(
        sortDescriptors: [NSSortDescriptor(keyPath: \MovieEntity.id, ascending: true)],
        animation: .default)
    private var items: FetchedResults<MovieEntity>

        var body: some View {
            List(viewModel.movies, id: \.id) { movie in
                HStack {
                    AsyncImage(url: URL(string: movie.imageResourceId)) { phase in
                        // Placeholder view while loading
                        if phase.image == nil {
                            ProgressView()
                        } else {
                            // Image loaded successfully
                            phase.image!
                                .resizable()
                                .frame(width: 100, height: 100)
                        }
                    }
                    VStack(alignment: .leading) {
                        Text(movie.title)
                            .font(.title)
                        HStack {
                            ForEach(0..<5) { star in
                                Image(systemName: star < Int(movie.rating/2) ? "star.fill" : "star")
                                    .foregroundColor(.yellow)
                            }
                        }
                        Button(action: {
                                                // Toggle the favorite state of the movie
                                                viewModel.toggleFavorite(for: movie)
//                            let movieE = MovieEntity(context: moc)
//                            movieE.id = movie.id
//                            movieE.title = movie.title
//                            movieE.imageResourceId = movie.imageResourceId
//                            movieE.rating = movie.rating
//                            
//                            try? moc.save()
                                            
                                            }) {
                                                Text(movie.isFavorite ? "Favorite" : "Mark as Favorite")
                                                    .foregroundColor(.white)
                                                    .padding(.horizontal)
                                                    .background(Color.blue)
                                                    .cornerRadius(8)
                                            }
                                            .buttonStyle(BorderlessButtonStyle())
//
                    }
                }
            }
        }
    
    private func saveFavoriteMovie(){
        
    }
}
