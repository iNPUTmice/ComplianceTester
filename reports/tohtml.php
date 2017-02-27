#!/usr/bin/php
<!DOCTYPE html>
<html>
  <head>
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>XMPP Compliance Suite 2016: Server Test Results</title>
    <meta property="og:description" content="An overview over the extensions (XEPs) that are enabled on various Jabber/XMPP servers. If all extensions are enabled the server passes the XMPP Compliance Suite 2016.">
    <meta name="description" content="An overview over the extensions (XEPs) that are enabled on various Jabber/XMPP servers. If all extensions are enabled the server passes the XMPP Compliance Suite 2016.">
    <meta property="og:locale" content="en_US">
    <meta charset="UTF-8">
    <style type="text/css">
      body {
        color: rgba(0,0,0,0.87);
        font-family: 'Roboto', sans-serif;
        font-weight: 400;
        font-size: 13pt;
        background-color: #fafafa;
      }
      table {
        width: 100%;
      }
      table th.nostretch {
        width: 1%;
        white-space: nowrap;
      }
      table tbody tr td {
        white-space: nowrap;
      }
      table tbody tr td.passed {
        background-color: #43a047;
      }
      table tbody tr td.failed {
        background-color: #e53935;
      }
      table tbody tr td.missing {
        background-color: #757575;
      }
      table tr:hover td.passed {
        background-color: #2e7d32;
      }
      table tr:hover td.failed {
        background-color: #c62828;
      }
      table tr:hover td.missing {
        background-color: #424242;
      }
      table tr:hover td {
        background-color: #e0e0e0;
      }
      a {
        color: #3f51b5;
      }
      p.small {
        font-size: 10pt;
      }
      div.banner {
        margin-left: auto;
        margin-right: auto;
        padding-left: 10pt;
        padding-right: 11pt;
        width: 60%;
        min-width: 640px;
        max-width: 1024px;
        border-width: 1px;
        border-style: solid;
        border-color: rgba(0,0,0,0.12);
      }
      .banner p {
        color: rgba(0,0,0,0.54);
        font-size: 11pt;
      }
      th.rotated {
        font-weight: normal;
        font-size: 80%;
        text-align: left;
        height: 175px;
        white-space: nowrap;
      }
      th.rotated > div {
        transform:
          translate(17px, 54px)
          rotate(315deg);
        width: 55px;
      }
    </style>
  </head>
  <body>
<?php
function count_frequency($needle, $reports) {
  $count = 0;
  foreach($reports as $report) {
    foreach($report as $key => $value) {
      if ($key === $needle && $value == "PASSED") {
        $count++;
      }
    }
  }
  return $count;
}
function comp_header($a, $b) {
  $reports = $GLOBALS["reports"];
  $count_a = count_frequency($a, $reports);
  $count_b = count_frequency($b, $reports);
  if ($count_a == $count_b) {
    return 0;
  }
  return ($count_a > $count_b) ? -1 : 1;
}
function comp_report($a, $b) {
  $count_a = calc_score($a);
  $count_b = calc_score($b);
  if ($count_a == $count_b) {
    return 0;
  }
  return ($count_a > $count_b) ? -1 : 1;
}
function calc_score($report) {
  $headers = $GLOBALS["headers"];
  $score = 0;
  $i = 1;
  foreach($headers as $key) {
    if (array_key_exists($key,$report) && 'PASSED' === $report[$key]) {
      $score += pow(2,$i);
    }
    $i++;
  }
  return $score;
}
$reports = json_decode(file_get_contents('complete.json'),true);
$headers = array();
foreach($reports as &$report) {
  $h = array_keys($report);
  if (count($h) > count($headers)) {
    $headers = $h;
  }
}
if (count($argv) >= 2 && $argv[1] === 'ranked') {
  usort($headers, "comp_header");
  uasort($reports, "comp_report");
}
?>
<div class="banner">
  <p><strong>Advertisement</strong><br>Are looking for an XMPP server that is very well maintained, always has the latest features and is running in a German data center?
<br>Are you willing to spend a little money for a reliable service? Have a look at <a href="https://account.conversations.im">conversations.im</a>. Your first six month are free. No cancelation required.</p>
  <p>We are also offering <a href="https://account.conversations.im/domain">Jabber/XMPP domain hosting</a> if you want to bring your own domain.</p>
</div>
<table>
  <thead>
    <tr>
      <th class="nostretch"></th>
<?php
  foreach($headers as &$head) {
    if (substr($head, 0, 3) === "XEP") {
      list($head_xep, $head_rest) = explode(": ", $head, 2);
      echo "      <th class=\"rotated\"><div>".htmlentities($head_xep).":<br>".htmlentities($head_rest)."</div></th>\n";
    } else {
      echo "      <th class=\"rotated\"><div>".htmlentities($head)."</div></th>\n";
    }
  }
?>
    </tr>
  </thead>
  <tbody>
<?php
foreach($reports as $server => $report) {
  echo '    <tr>';
  echo '<td>'.htmlentities($server).'</td>';
  foreach($headers as $c) {
    if (!array_key_exists($c, $report)) {
      $class = 'missing';
    } else if ('PASSED' === $report[$c]) {
      $class = 'passed';
    } else {
      $class = 'failed';
    }
    echo '<td class="'.$class.'"></td>';
  }
  echo "</tr>\n";
}
?>
  </tbody>
</table>
<p class="small">Copyright 2016 <a href="https://gultsch.de">Daniel Gultsch</a> &middot; Data gathered with <a href="https://github.com/iNPUTmice/ComplianceTester">XMPP Compliance Tester</a> &middot; Last update <?= date("Y-m-d")?> (actual data might be older)</p>
</body>
</html>
