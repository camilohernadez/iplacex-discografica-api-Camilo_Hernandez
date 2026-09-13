package org.iplacex.Discografia.Artistas;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IArtistaRepository extends MongoRepository<Artista, String> {

}
