<?php

$GLOBALS['THRIFT_ROOT'] = './thrift/lib/php/src';

require_once $GLOBALS['THRIFT_ROOT'].'/Thrift.php';
require_once $GLOBALS['THRIFT_ROOT'].'/protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/transport/TBufferedTransport.php';

require_once $GLOBALS['THRIFT_ROOT'].'/packages/PalinTheorem.php';
require_once $GLOBALS['THRIFT_ROOT'].'/packages/PalinTheorem_types.php';

try {
  $socket = new TSocket('localhost', 9090);
  $transport = new TBufferedTransport($socket, 1024, 1024);
  $protocol = new TBinaryProtocol($transport);
  $client = new PalinTheoremClient($protocol);

  $transport->open();

  $quote = $client->genQuote();
  echo $quote;

  $transport->close();

} catch (TException $tx) {
  print 'TException: '.$tx->getMessage()."\n";
}