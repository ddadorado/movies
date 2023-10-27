//
//  MoviesViewModel.swift
//  moviesMp
//
//  Created by Training Sj on 10/26/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI
import CoreData

class AppDelegate: NSObject, UIApplicationDelegate {
    
    let container = NSPersistentContainer(name: "Model")
    override init(){
        container.loadPersistentStores { description, error in
            if let error = error {
                print("Core data failed to load \(error.localizedDescription)")
            }
        }
    }
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        print("Your code here")
        return true
    }
}


class MoviesViewModel: ObservableObject {
    let movieClient = MovieClient()
    @Published var movies: [MovieData] = []
    let appDelegate : AppDelegate
    let context : NSManagedObjectContext
    //@Environment(\.managedObjectContext) var moc
    //@FetchRequest(entity: MovieEntity.entity(), sortDescriptors: [NSSortDescriptor(keyPath: \MovieEntity.id, ascending: true)]) var favoriteMovies: FetchedResults<MovieEntity>

    //@FetchRequest(sortDescriptors: []) var favorite: FetchRequest<MovieEntity>
    //var repo : MovieRepository = MovieRepository(IdbManager: CoredatabaseManager2())

    init() {
        appDelegate = UIApplication.shared.delegate as! AppDelegate
        context  = appDelegate.container.viewContext
        DispatchQueue.global().async {
            Task { @MainActor in
                do {
                    self.movies = try await self.movieClient.fetchMovies()
                    
                } catch {
                    print("Error fetching movies: \(error)")
                }
            }
        }
    }


    func toggleFavorite(for movie: MovieData) {
        if let index = movies.firstIndex(where: { $0.id == movie.id }) {
            self.movies[index].isFavorite.toggle()
//            if self.movies[index].isFavorite {
//                repo.saveMovie(movie: self.movies[index])
//                    }
            let entity = NSEntityDescription.entity(forEntityName: "MovieEntity", in: context)
            let newMovie = NSManagedObject(entity: entity!, insertInto: context)
            newMovie.setValue(movie.id, forKey: "id")
            newMovie.setValue(movie.title, forKey: "title")
            newMovie.setValue(movie.imageResourceId, forKey: "imageResourceId")
            newMovie.setValue(movie.rating, forKey: "rating")
            newMovie.setValue(movie.isFavorite, forKey: "isFavorite")
            do {
              try context.save()
             } catch {
              print("Error saving")
            }
//            let movieE = MovieEntity(context: moc)
//            movieE.id = movie.id
//            movieE.title = movie.title
//            movieE.imageResourceId = movie.imageResourceId
//            movieE.rating = movie.rating
//            try? moc.save()
            objectWillChange.send()
        }
    }

    func getFavoriteMovies() -> [MovieData] {
        //return self.movies.filter { $0.isFavorite }
        return []
//        return favoriteMovies.map { movieEntity in
//                    MovieData(
//                        imageResourceId: movieEntity.imageResourceId ?? "",
//                        id: Int32(movieEntity.id),
//                        title: movieEntity.title ?? "",
//                        rating: movieEntity.rating,
//                        isFavorite: true
//                    )
//                }
    }

    func fetchMovies() async throws -> [MovieData] {
        return try await movieClient.fetchMovies()
    }
}
