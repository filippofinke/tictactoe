package ch.supsi.tictactoe.persistence;

import ch.supsi.tictactoe.exceptions.FailedToLoadGameException;
import ch.supsi.tictactoe.exceptions.FailedToSaveGameException;
import ch.supsi.tictactoe.gamelogic.Move;

import java.nio.file.Path;
import java.util.List;

public interface GamePersistenceManager {
    void save(Path destination, List<Move> moves) throws FailedToSaveGameException;
    List<Move> load(Path source) throws FailedToLoadGameException;
}
