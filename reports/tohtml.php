#!/usr/bin/php
<!DOCTYPE html>
<html>
  <head>
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <style type="text/css">
      body {
        color: rgba(0,0,0,0.87);
        font-family: 'Roboto', sans-serif;
        font-weight: 400;
        font-size: 13pt;
        background-color: #fafafa;
      }
      table tbody tr td.passed {
        background-color: #43a047;
      }
      table tbody tr td.failed {
        background-color: #e53935;
      }
      table tr:hover td.passed {
        background-color: #2e7d32;
      }
      table tr:hover td.failed {
        background-color: #c62828;
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
    </style>
  </head>
  <body>
<?php
$reports = json_decode(file_get_contents('complete.json'),true);
$headers = array_keys(reset($reports));
?>
<table>
  <thead>
    <tr>
      <th></th>
      <?php
        foreach($headers as &$head) {
          echo "<th>".htmlentities($head)."</th>";
        }
      ?>
    </tr>
  </thead>
  <tbody>
    <?php
    foreach($reports as $server => $report) {
      echo '<tr>';
      echo '<td>'.htmlentities($server).'</td>';
      foreach($report as $data) {
        $pass = 'PASSED' === $data;
        echo '<td class="';
        echo $pass ? 'passed' : 'failed';
        echo '"></td>';
      }
      echo '</tr>';
    }
    ?>
  </tbody>
</table>
<p class="small">Copyright 2016 <a href="https://gultsch.de">Daniel Gultsch</a> &middot; Data gathered with <a href="https://github.com/iNPUTmice/ComplianceTester">XMPP Compliance Tester</a> &middot; Last update <?= date("Y-m-d")?> (actual data might be older)</p>
</body>
</html>
