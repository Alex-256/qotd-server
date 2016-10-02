import Network.Socket
import Data.List.Split
import System.Random

data Quote = Quote 
  { getBody :: !String
  , getPerson :: !String
  } deriving (Show)

splitQuote :: String -> Quote
splitQuote quote = Quote (parts !! 0) (parts !! 1)
  where parts = splitOn "|" quote

main :: IO ()
main = do
  quotes <- map splitQuote <$> filter (/="") <$> splitOn "\n" <$> readFile "../qotd.txt"
  sock <- socket AF_INET Stream 0
  setSocketOption sock ReuseAddr 1
  bind sock (SockAddrInet 17 iNADDR_ANY)
  listen sock 2
  mainLoop sock quotes

mainLoop :: Socket -> [Quote] -> IO ()
mainLoop sock quotes = do
  conn <- accept sock
  next <- runConn conn quotes
  mainLoop sock quotes

runConn :: (Socket, SockAddr) -> [Quote] -> IO ()
runConn (sock, _) quotes = do
  quote <- (quotes !!) <$> getStdRandom (randomR (0, (length quotes)-1))
  send sock $ "\n\"" ++ (getBody quote) ++ "\"\n\n- " ++ (getPerson quote) ++ "\n\n"
  close sock
